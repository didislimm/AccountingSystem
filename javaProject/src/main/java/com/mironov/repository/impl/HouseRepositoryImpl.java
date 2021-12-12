package com.mironov.repository.impl;

import com.mironov.model.House;
import com.mironov.repository.HouseRepository;

import java.util.*;

public class HouseRepositoryImpl implements HouseRepository {
    private static final Map<Integer, House> houses = new HashMap<>();

    private static HouseRepository instance = null;

    private HouseRepositoryImpl() {
    }

    public synchronized static HouseRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new HouseRepositoryImpl();
        }
        return instance;
    }

    public void add(final House house) {
        String sql="INSERT INTO HOUSES columns(NUMBER_OF_HOUSE) values(?)";
        houses.put(house.getNumberOfHouse(), house);
    }

    public Optional<House> getByKey(Integer key) {
        return Optional.ofNullable(houses.get(key));
    }

    public List<House> getAll() {
        return List.copyOf(houses.values());
    }

    public List<Integer> getAllKey() {
        return List.copyOf(houses.keySet());
    }


    public void removeByKey(Integer key) {
        houses.remove(key);
    }
}
