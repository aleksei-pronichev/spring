package ru.pronichev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.EntityManagerFactory;

@org.springframework.context.annotation.Configuration
@ComponentScan("ru.pronichev")
public class AppConfig {

    @Bean(name = "manager")
    public EntityManagerFactory getFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.xml")
                .buildSessionFactory();
    }
} 