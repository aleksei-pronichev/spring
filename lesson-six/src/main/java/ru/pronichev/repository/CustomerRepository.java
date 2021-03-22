package ru.pronichev.repository;

import org.springframework.stereotype.Repository;
import ru.pronichev.data.Customer;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomerRepository extends CRUDimpl<Customer> {
    public CustomerRepository(EntityManager manager) {
        super(manager, Customer.class);
    }

    public List<Customer> getProductsCustomers(Long productID) {
        String request = String.format(
                "SELECT * FROM customers c JOIN realizations r ON c.id = r.customer_id where product_id = %d",
                productID
        );
        return sqlRequest(request);
    }
}