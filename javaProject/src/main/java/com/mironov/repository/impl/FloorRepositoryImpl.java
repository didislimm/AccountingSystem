package com.mironov.repository.impl;

import com.mironov.model.Floor;
import com.mironov.repository.FloorRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FloorRepositoryImpl implements FloorRepository {

    private static FloorRepository instance = null;

    private FloorRepositoryImpl() {

    }

    public synchronized static FloorRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new FloorRepositoryImpl();
        }
        return instance;
    }


    @Override
    public void add(Floor entity) {

    }

    @Override
    public Optional<Floor> getByKey(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Floor> getAll() {
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
