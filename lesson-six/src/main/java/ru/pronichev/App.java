package ru.pronichev;

import org.hibernate.cfg.Configuration;
import ru.pronichev.data.Product;
import ru.pronichev.repository.CRUD;
import ru.pronichev.repository.CRUDimpl;

import javax.persistence.EntityManagerFactory;

public class App {
    private static CRUD<Product> productRepo;

    public static void main(String[] args) {
        EntityManagerFactory factory = getFactory();
        productRepo = new CRUDimpl<>(factory.createEntityManager(), Product.class);

        Product product = testNewProduct();
        testUpdateProduct(product);
        testRemove(1L);
        testFindProduct();
        testFindAllProduct();
    }

    private static Product testNewProduct() {
        Product product = new Product();
        product.setTitle("Apple");
        product.setPrice(1);
        productRepo.saveOrUpdate(product);

        product = new Product();
        product.setTitle("Orange");
        product.setPrice(2);
        productRepo.saveOrUpdate(product);

        product = new Product();
        product.setTitle("What");
        product.setPrice(10);
        return productRepo.saveOrUpdate(product);
    }

    private static void testUpdateProduct(Product product) {
        product.setTitle("Pine Apple");
        productRepo.saveOrUpdate(product);
    }

    private static void testRemove(long id) {
        productRepo.deleteById(id);
    }

    private static void testFindProduct() {
        System.out.println(productRepo.findByID(2L));
        System.out.println("--------");
    }

    private static void testFindAllProduct() {
        productRepo.findALl()
                .forEach(System.out::println);
    }

    private static EntityManagerFactory getFactory() {
        return new Configuration()
                .configure("hibernate.xml")
                .buildSessionFactory();
    }
} 