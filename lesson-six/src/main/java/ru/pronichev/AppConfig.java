package ru.pronichev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.EntityManager;

@org.springframework.context.annotation.Configuration
@ComponentScan("ru.pronichev")
public class AppConfig {

    @Bean(name = "manager")
    public EntityManager manager() {
        return new org.hibernate.cfg.Configuration()
//                .configure("hibernatePostgres.xml")
                .configure("hibernateMySQL.xml")
                .buildSessionFactory()
                .createEntityManager();
    }
} 