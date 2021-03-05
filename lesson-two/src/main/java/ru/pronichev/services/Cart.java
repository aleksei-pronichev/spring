package ru.pronichev.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.pronichev.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private final List<Product> products;
    private final ProductRepository productRepository;

    @Autowired
    public Cart(ProductRepository productRepository) {
        this.products = new ArrayList<>();
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return Collections.unmodifiableList(products);
    }

    public void putByID(int id) {
        products.add(productRepository.getByID(id));
    }

    public Product remove(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                Product product = products.get(i);
                products.remove(i);
                return product;
            }
        }
        throw new CartException(id, "Product not found");
    }

    @Getter
    public static class CartException extends IndexOutOfBoundsException {
        private final int productID;

        public CartException(int productID, String message) {
            super(message);
            this.productID = productID;
        }
    }
}