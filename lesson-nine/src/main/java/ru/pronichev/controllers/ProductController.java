package ru.pronichev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.pronichev.data.Product;
import ru.pronichev.data.dto.ProductDTO;
import ru.pronichev.services.ProductService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product get(@PathVariable(value = "id") long id) {
        return productService.get(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAll() {
        return productService.getAll();

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Product add(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Product update(@RequestBody Product product) {
        return productService.save(productDTO);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Long id) {
        productService.delete(id);
        return HttpStatus.OK.value();
    }
}
