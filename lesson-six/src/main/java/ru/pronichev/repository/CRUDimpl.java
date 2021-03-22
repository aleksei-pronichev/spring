package ru.pronichev.repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CRUDimpl<T> implements CRUD<T> {

    private final EntityManager manager;
    private final Class<T> clazz;
    private final String table;

    public CRUDimpl(EntityManager manager, Class<T> clazz) {
        this.manager = manager;
        this.clazz = clazz;
        this.table = clazz.getSimpleName();
    }

    @Override
    public T findByID(Long id) {
        String query = String.format("select a from %s a where id = %d", table, id);
        return manager
                .createQuery(query, clazz)
                .getSingleResult();
    }

    @Override
    public List<T> findALl() {
        String query = String.format("select a from %s a", table);
        return manager
                .createQuery(query, clazz)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        T entity = manager.find(clazz, id);
        manager.remove(entity);
        transaction.commit();
    }

    @Override
    public T saveOrUpdate(T entity) {
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        T newEntity = manager.merge(entity);
        transaction.commit();

        return newEntity;
    }

    protected List<T> sqlRequest(String request) {
        return manager.createNativeQuery(request, clazz).getResultList();
    }
} 