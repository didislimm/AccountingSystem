package com.mironov.repository;

import java.util.List;
import java.util.Optional;

public interface Repository <PK, T>{

    void add(final T entity) ;

    Optional<T> getByKey(final PK key) ;

    List<T> getAll();

    void removeByKey(final PK key) ;

    List<PK> getAllKey();
}
