package com.mironov.repository.impl;

import com.mironov.model.House;
import com.mironov.repository.HouseRepository;

import java.util.*;

public class HouseRepositoryImpl implements HouseRepository {
    private static final Map<Integer, House> houses = new HashMap<>();

    public void add(final House house) {
        houses.put(house.getNumberOfHouse(), house);
    }

    public  Optional<House> getByKey(Integer key) {
        return Optional.ofNullable(houses.get(key));
    }

    public List<House> getAll() {
        return List.copyOf(houses.values());
    }

    public List<Integer> getAllKey(){ return List.copyOf(houses.keySet());}


    public void removeByKey(Integer key) {
        houses.remove(key);
    }
}
