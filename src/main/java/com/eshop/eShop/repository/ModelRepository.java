package com.eshop.eShop.repository;

import com.eshop.eShop.domain.Category;
import com.eshop.eShop.domain.Model;
import com.eshop.eShop.dto.ModelDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long> {

    List<Model> findAllByIsActive(Boolean isActive);
    Model findByName(String name);

    @Query(value = "select * from model where is_active = 1 and name like :name",nativeQuery = true)
    List<Model> findAllByIsActiveWithSearch(String name);
}
