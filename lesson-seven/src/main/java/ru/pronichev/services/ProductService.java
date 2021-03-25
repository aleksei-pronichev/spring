package ru.pronichev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pronichev.data.Product;
import ru.pronichev.data.dto.ProductDTO;
import ru.pronichev.repository.ProductRepository;
import ru.pronichev.repository.RepositoryException;

import java.util.List;

@Component
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product get(long id) {
        return productRepository.findById(id).orElseThrow(() -> new RepositoryException("product not found"));
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product save(ProductDTO productDTO) {
        Product product = new Product();
        product.setPrice(productDTO.getPrice());
        product.setTitle(productDTO.getTitle());
        return productRepository.save(product);
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllMax(Integer max) {
        return productRepository.findByPriceLessThanEqual(max);
    }

    public List<Product> getAllMin(Integer min) {
        return productRepository.findByPriceGreaterThanEqual(min);
    }

    public List<Product> getAllMinMax(Integer min, Integer max) {
        return productRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(min, max);
    }
}