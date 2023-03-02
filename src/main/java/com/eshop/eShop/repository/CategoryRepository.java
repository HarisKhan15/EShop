package com.eshop.eShop.repository;

import com.eshop.eShop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findAllByIsActive(Boolean isActive);
    Category findByName(String name);

    @Query(value = "select * from category where is_active = 1 and name like :name",nativeQuery = true)
    List<Category> findAllByIsActiveWithSearch(String name);
}
