package ru.pronichev.repository;

import java.util.List;

public interface CRUD<T> {
    T findByID(Long id);

    List<T> findALl();

    void deleteById(Long id);

    T saveOrUpdate(T entity);
} 