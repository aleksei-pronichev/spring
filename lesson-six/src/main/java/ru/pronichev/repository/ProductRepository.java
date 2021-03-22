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
                "SELECT p FROM Product p JOIN Realization r ON r.id = p.id WHERE r.customer_id = %d",
                customerID
        );
        return sqlRequest(request);
    }
}
