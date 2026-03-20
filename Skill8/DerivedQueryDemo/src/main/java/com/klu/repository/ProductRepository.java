package com.klu.repository;

import com.klu.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);
    Product findByName(String name);
    List<Product> findByCategoryAndPriceGreaterThan(String category, double price);
    List<Product> findByCategoryOrName(String category, String name);
    List<Product> findByPriceBetween(double min, double max);
    List<Product> findByNameLike(String pattern);
    List<Product> findByPriceGreaterThan(double price);
    long countByCategory(String category);
    boolean existsByName(String name);

    @Transactional
    void deleteByName(String name);

    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> sortByPrice();
}