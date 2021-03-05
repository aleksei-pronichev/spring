package ru.pronichev.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pronichev.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private final List<Product> products;

    @Autowired
    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public List<Product> getAll() {
        return Collections.unmodifiableList(products);
    }

    public Product getByID(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new RepositoryException(id, "Product not found");
    }

    public void save(Product product) {
        products.add(product);
    }

    @Getter
    public static class RepositoryException extends IndexOutOfBoundsException {
        private final int productID;

        public RepositoryException(int productID, String message) {
            super(message);
            this.productID = productID;
        }
    }

    @PostConstruct
    private void init() {
        products.add(new Product(1, "Apple", 2));
        products.add(new Product(2, "Orange", 3));
        products.add(new Product(3, "Banana", 4));
        products.add(new Product(4, "Mango", 5));
        products.add(new Product(5, "Potato", 1));
    }
}