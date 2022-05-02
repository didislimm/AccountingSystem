package com.mironov.util;

import com.mironov.model.Flat;
import com.mironov.model.Floor;
import com.mironov.model.House;

import java.util.ArrayList;
import java.util.Objects;

public class HouseGenerator {
    private static int flatNumber = 0;
    private static int floorNumber = 0;
    private final int valueOfFloors;
    private static HouseGenerator instance=null;

    private HouseGenerator(int valueOfFloors) {
        this.valueOfFloors = valueOfFloors;
    }

    public int getValueOfFloors() {
        return valueOfFloors;
    }

    public static synchronized HouseGenerator getInstance(int valueOfFloors) {
        if (Objects.isNull(instance)){
            instance=new HouseGenerator(valueOfFloors);
        }
        return instance;
    }

    public static Double getRandomDoubleInRange(int min, int max) {
        max -= min;
        return (Math.random() * ++max) + min;
    }

    public static Flat createRandomFlat(double squareOfFlat) {
        int randomNumberOfRoom = getRandomDoubleInRange(1, 4).intValue();
        int randomNumberOfLodger = getRandomDoubleInRange(0, 6).intValue();
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
