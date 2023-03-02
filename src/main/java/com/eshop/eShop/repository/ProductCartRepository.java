package com.eshop.eShop.repository;

import com.eshop.eShop.domain.Cart;
import com.eshop.eShop.domain.Product;
import com.eshop.eShop.domain.ProductCart;
import com.eshop.eShop.dto.ProductCartDto;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@NamedNativeQuery(
        name = "find_by_cart_id",
        query = "select * from product_cart pc where pc.cart_id = :id",
        resultSetMapping = "product_cart_dto"
)
@SqlResultSetMapping(
        name = "product_cart_dto",
        classes = @ConstructorResult(
                targetClass = ProductCartDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "quantity", type = Integer.class),
                        @ColumnResult(name = "price", type = Double.class),
                        @ColumnResult(name = "cost", type = Double.class),
                        @ColumnResult(name = "product", type = Product.class),
                        @ColumnResult(name = "cart", type = Cart.class)

                }
        )
)
@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart,Long> {
    @Query(value = "find_by_cart_id",nativeQuery = true)
    List<ProductCart> findByCartId(@Param("id") Long id);

}
