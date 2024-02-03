package com.example.DAO;

import java.util.Collection;
import java.util.Optional;

public interface GenericDAO<T, I> { // T is object, I is primary key
    Optional<T> get(I i) throws ClassNotFoundException;

    Collection<T> getAll() throws ClassNotFoundException;

    void save(T t) throws ClassNotFoundException;

    void update(I i, T t) throws ClassNotFoundException;

    void delete(I i) throws ClassNotFoundException;
}
