package com.eshop.eShop.repository;

import com.eshop.eShop.domain.Category;
import com.eshop.eShop.domain.Model;
import com.eshop.eShop.domain.Product;
import com.eshop.eShop.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByIsActive(Boolean isActive);
    Product findByNameAndCategoryAndModel(String name, Category category, Model model);
}
