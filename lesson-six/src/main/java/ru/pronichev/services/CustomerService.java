package ru.pronichev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pronichev.CustomerDTO;
import ru.pronichev.data.Product;
import ru.pronichev.repository.CustomerRepository;
import ru.pronichev.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public List<Product> getCustomerProducts(Long customerId) {
        return productRepository.getCustomerProducts(customerId);
    }

    public List<CustomerDTO> getProductCustomers(Long productId) {
        return customerRepository.getProductsCustomers(productId)
                .stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());
    }
}
