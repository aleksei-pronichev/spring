package ru.pronichev.repository;

import org.springframework.stereotype.Repository;
import ru.pronichev.data.Product;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProductRepository extends CRUDimpl<Product> {
    public ProductRepository(EntityManager manager) {
        super(manager, Product.class);
    }

    public List<Product> getCustomerProducts(Long customerID) {

        String request = String.format(
                "SELECT * FROM products p JOIN realizations r ON p.id = r.product_id WHERE r.customer_id = %d",
                customerID
        );
        return sqlRequest(request);
    }
}
