package ru.pronichev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pronichev.data.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceLessThanEqual(int max);
    List<Product> findByPriceGreaterThanEqual(int min);
    List<Product> findByPriceGreaterThanEqualAndPriceLessThanEqual(int min, int max);
}