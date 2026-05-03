package com.pioneers.ordertask.database;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;

@Slf4j
public class DatabaseImpl<T, ID> implements Database<T, ID> {

    private final Map<ID, T> data = new LinkedHashMap<>();
    private final Function<T, ID> idExtractor;

    public DatabaseImpl(final Function<T, ID> idExtractor){
        this.idExtractor = idExtractor;
    }

    private ID extractId(T entity){
        return idExtractor.apply(entity);
    }

    @Override
    public T save(T t) {
        log.info("saving entity {} to database", t);
        data.put(extractId(t), t);
        return t;
    }

    @Override
    public Optional<T> findById(ID id) {
        log.info("finding entity by id");
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Collection<T> findAll() {
        log.info("finding all entity");
        return data.values();
    }

    @Override
    public void removeById(ID id) {
        log.info("removing entity by id");
        data.remove(id);
    }

    @Override
    public boolean exists(ID id) {
        log.info("checking if entity exists");
        return data.containsKey(id);
    }

}
