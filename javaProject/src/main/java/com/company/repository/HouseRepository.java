package com.company.repository;

import com.company.model.House;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HouseRepository {
    private static final Map<Integer, House> houses = new HashMap<>();

    public static void addHouse(final House house) {
        houses.put(house.getNumberOfHouse(), house);
    }

    public static Optional<House> getHouseByNumber(final int numberOfHouse) {
        return Optional.ofNullable(houses.get(numberOfHouse));
    }

    public static void removeHouse(final int numberOfHouse) {
        houses.remove(numberOfHouse);
    }

    public static boolean isRepositoryNull() {
        return houses.isEmpty();
    }

    public static boolean isRepositoryHaveTwoHouses() {
        return houses.size() < 2;
    }

}
