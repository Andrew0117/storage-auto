package org.storage.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.storage.entity.Brand;
import org.storage.entity.Model;
import org.storage.filter.AutoFilter;
import org.storage.jpa.BrandJpa;
import org.storage.jpa.ModelJpa;
import org.storage.vw.StorageAutoVW;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StorageAutoService {

    private BrandJpa brandJpa;

    private ModelJpa modelJpa;

    @PersistenceContext
    private EntityManager entityManager;

    public StorageAutoService(BrandJpa brandJpa, ModelJpa modelJpa) {
        this.brandJpa = brandJpa;
        this.modelJpa = modelJpa;
    }

    public List<StorageAutoVW> searchStorageAuto(AutoFilter autoFilter) {
        List<StorageAutoVW> storageAutoVWList = new ArrayList<>();

        StringBuffer where = new StringBuffer();
        if (autoFilter.getBrand() != null && !autoFilter.getBrand().equals("")) {
            where.append("and b.brand = '" + autoFilter.getBrand() + "'");
        }
        if (autoFilter.getModel() != null && !autoFilter.getModel().equals("")) {
            where.append("and m.model = '" + autoFilter.getModel() + "'");
        }

        String where_ = where.toString();

        String sql = """
                    select b.id as idBrand, m.id as idModel, b.brand, m.model, m.comment, m.color, m.price
                    from brand b
                    left join model m on b.id = m.fkBrand
                """ + (where_ != null && !where_.equals("") ? " where " + where_.substring(4) : "");

        List<Object[]> list = entityManager.createNativeQuery(sql).getResultList();

        for (Object[] a : list) {
            StorageAutoVW storageAutoVW = new StorageAutoVW();
            storageAutoVW.setIdBrand(UUID.fromString(String.valueOf(a[0])));
            storageAutoVW.setIdModel(UUID.fromString(String.valueOf(a[1])));
            storageAutoVW.setBrand((String) a[2]);
            storageAutoVW.setModel((String) a[3]);
            storageAutoVW.setComment((a[4] != null ? (String) a[4] : ""));
            storageAutoVW.setColor((a[5] != null ? (String) a[5] : ""));
            storageAutoVW.setPrice((a[6] != null ? (BigDecimal) a[6] : null));

            storageAutoVWList.add(storageAutoVW);
        }

        return storageAutoVWList;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public StorageAutoVW saveStorageAuto(StorageAutoVW storageAutoVW) {
        Brand brand = storageAutoVW.toBrand();

        Model model = storageAutoVW.toModel();

        Brand findBrand = brandJpa.findByBrand(brand.getBrand());

        if (findBrand != null) {
            model.setBrand(findBrand);
            model = modelJpa.save(model);

            return new StorageAutoVW(findBrand, model);
        } else {
            model.setBrand(brand);

            brand.getModels().add(model);

            brand = this.brandJpa.save(brand);
            model = brand.getModels().get(0);

            return new StorageAutoVW(brand, model);
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteStorageAuto(UUID idBrand, UUID idModel) {
        Long count = this.modelJpa.findCountModelsByBrandId(idBrand);

        if (count > 1) {
            this.modelJpa.deleteModelById(idModel);
        } else {
            this.modelJpa.deleteModelById(idModel);

            this.brandJpa.deleteBrandById(idBrand);
        }
    }
}
