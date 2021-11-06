package com.company.repository;

import com.company.domain.House;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HouseRepository {
    private static final Map<Integer,House> intToHouse=new HashMap<>();

    public static void addHouse(final House house){
        intToHouse.put(house.getNumberOfHouse(),house);
    }
    public static Optional<House> getHouseByNumber(final int numberOfHouse){
        return Optional.ofNullable(intToHouse.get(numberOfHouse));
    }

}
