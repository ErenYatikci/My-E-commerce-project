package com.myproject.backend.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);
    List<Product> findAllByProductType(ProductType productType);
//    Optional<Product> findById(Long id);

    @Query("SELECT p FROM Product p WHERE p.productType=?1 AND p.category=?2")
    List<Product> findAllByCategoryAndSex(ProductType productType, Category category);
}
