package com.mironov.repository.impl;

import com.mironov.model.Flat;
import com.mironov.repository.FlatRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FlatRepositoryImpl implements FlatRepository {

    private static FlatRepository instance=null;

    private FlatRepositoryImpl(){

    }

    public static synchronized FlatRepository getInstance(){
        if (Objects.isNull(instance)){
            instance=new FlatRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void add(Flat entity) {

    }

    @Override
    public Optional<Flat> getByKey(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Flat> getAll() {
        return null;
    }

    @Override
    public List<Integer> getAllKey() {
        return null;
    }

    @Override
    public void removeByKey(Integer key) {

    }
}
