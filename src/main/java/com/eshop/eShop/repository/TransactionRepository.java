package com.eshop.eShop.repository;

import com.eshop.eShop.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query(value = "select SUM(quantity*price) from product_cart pc where pc.cart_id = :id",nativeQuery = true)
    Double getTotalAmount(@Param("id") Long id);
}
