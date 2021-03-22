package ru.pronichev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.pronichev.services.CustomerService;

public class App {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CustomerService service = context.getBean(CustomerService.class);

        service.getCustomerProducts(2L)
                .forEach(System.out::println);
        System.out.println("------");
        service.getProductCustomers(2L)
                .forEach(System.out::println);
    }
}