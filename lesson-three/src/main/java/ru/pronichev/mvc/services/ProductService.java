package ru.pronichev.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pronichev.mvc.data.Product;
import ru.pronichev.mvc.repo.ProductRepository;

import java.util.List;

@Component
public class ProductService {
    private ProductRepository repository;

    @Autowired
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    public void save(Product product) {
        repository.save(product);
    }

    public List<Product> getAll() {
        return repository.getAll();
    }

    public Product getByID(int i) {
        return repository.getByID(i);
    }
}