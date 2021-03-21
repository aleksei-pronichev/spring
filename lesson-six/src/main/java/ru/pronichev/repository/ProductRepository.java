package ru.pronichev.repository;

import org.springframework.stereotype.Repository;
import ru.pronichev.data.Product;

import javax.persistence.EntityManager;

@Repository
public class ProductRepository extends CRUDimpl<Product> {
    public ProductRepository(EntityManager manager) {
        super(manager, Product.class);
    }
}