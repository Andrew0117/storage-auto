package org.storage.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.storage.entity.Brand;

import java.util.UUID;

@Repository
public interface BrandJpa extends JpaRepository<Brand, UUID> {

    Brand findByBrand(String brand);

    @Modifying
    @Query("delete from Brand where id = :id")
    void deleteBrandById(@Param("id") UUID id);

}
