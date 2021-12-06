package com.mironov.util;

import com.mironov.model.*;

import java.util.ArrayList;

public class HouseGenerator {
    private final int valueOfFloors;
    private static int flatNumber = 0;
    private static int floorNumber = 0;

    public HouseGenerator(int valueOfFloors) {
        this.valueOfFloors = valueOfFloors;
    }

    public static Double getRandomDoubleInRange(int min, int max) {
        max -= min;
        return (Math.random() * ++max) + min;
    }

    public static Flat createRandomFlat(double squareOfFlat) {
        int randomNumberOfRoom = getRandomDoubleInRange(1, 4).intValue();
        int randomNumberOfLodger = getRandomDoubleInRange(1, 7).intValue();
        flatNumber++;
        return Flat.FlatBuilder.aFlat()
                .withNumberOfFlat(flatNumber)
                .withNumberOfLodger(randomNumberOfLodger)
                .withNumberOfRoom(randomNumberOfRoom)
                .withSquareOfFlat(squareOfFlat)
                .build();
    }

    public static Floor createRandomFloor(ArrayList<Integer> squareOfFlats) {
        floorNumber++;
        return Floor.FloorBuilder.aFloor().but(squareOfFlats, floorNumber).build();
    }

    public House createRandomHouse(ArrayList<Integer> squareOfFlats) {
        House house = House.HouseBuilder.aHouse().but(squareOfFlats, valueOfFloors).build();
        squareOfFlats.clear();
        flatNumber = 0;
        floorNumber = 0;
        return house;
    }
}
