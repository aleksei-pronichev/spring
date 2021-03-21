package ru.pronichev.repository;

import org.springframework.stereotype.Repository;
import ru.pronichev.data.Customer;

import javax.persistence.EntityManager;

@Repository
public class CustomerRepository extends CRUDimpl<Customer> {
    public CustomerRepository(EntityManager manager) {
        super(manager, Customer.class);
    }
}