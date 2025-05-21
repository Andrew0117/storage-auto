package org.storage.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.storage.entity.Model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ModelJpa extends JpaRepository<Model, UUID> {

    @Modifying
    @Query("delete from Model where id = :id")
    void deleteModelById(@Param("id") UUID id);

    @Query(value = """
                select count(m.id)
                from Model m
                where m.fkBrand = :id
            """, nativeQuery = true)
    Long findCountModelsByBrandId(@Param("id") UUID id);

    @Query(value = """
                select m.id, m.model, m.comment, m.color, m.price, m.fkBrand
                from Model m
                where m.fkBrand = :id
            """, nativeQuery = true)
    List<Model> findModelsByBrandId(@Param("id") UUID id);

    @Modifying
    @Query(value = "update model set comment = :comment where id = :id", nativeQuery = true)
    void saveComment(@Param("comment") String comment, @Param("id") UUID id);

    @Modifying
    @Query(value = "update model set color = :color, price = :price where id = :id", nativeQuery = true)
    void saveColorAndPrice(@Param("color") String color, @Param("price") BigDecimal price, @Param("id") UUID id);
}
