package ru.pronichev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pronichev.data.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}