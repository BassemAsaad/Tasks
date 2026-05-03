package com.pioneers.ordertask.database;

import java.util.Collection;
import java.util.Optional;

public interface Database<T, ID> {

    T save(T t);

    Optional<T> findById(ID id);

    Collection<T> findAll();

    void removeById(ID id);

    boolean exists(ID id);

}
