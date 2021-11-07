package com.company;

import com.company.domain.Flat;
import com.company.domain.Floor;
import com.company.domain.House;

import java.util.ArrayList;

public class HouseGenerator {
    private final int valueOfFlats;
    private final int valueOfFloors;
    private int flatNumber = 0;
    private int floorNumber = 1;

    public HouseGenerator(int valueOfFlats, int valueOfFloors) {
        this.valueOfFlats = valueOfFlats;
        this.valueOfFloors = valueOfFloors;
    }

    public int getValueOfFlats() {
        return valueOfFlats;
    }

    public int getValueOfFloors() {
        return valueOfFloors;
    }

    public static Double getRandomDoubleInRange(int min, int max) {
        max -= min;
        return (Math.random() * ++max) + min;
    }

    public Flat createRandomFlat() {
        double randomSquareOfFlat = getRandomDoubleInRange(40, 130);
        int randomNumberOfRoom = getRandomDoubleInRange(1, 5).intValue();
        int randomNumberOfLodger = getRandomDoubleInRange(1, 6).intValue();
        flatNumber++;
        return Flat.FlatBuilder.aFlat()
                .withSquareOfFlat(randomSquareOfFlat)
                .withNumberOfRoom(randomNumberOfRoom)
                .withNumberOfLodger(randomNumberOfLodger)
                .withNumberOfFlat(flatNumber)
                .build();
    }

    public Flat createFlat(double squareOfFlat) {
        int randomNumberOfRoom = getRandomDoubleInRange(1, 5).intValue();
        int randomNumberOfLodger = getRandomDoubleInRange(1, 6).intValue();
        flatNumber++;
        return Flat.FlatBuilder.aFlat()
                .withSquareOfFlat(squareOfFlat)
                .withNumberOfRoom(randomNumberOfRoom)
                .withNumberOfLodger(randomNumberOfLodger)
                .withNumberOfFlat(flatNumber)
                .build();
    }

    public Floor createRandomFloor() {
        Floor floor = new Floor();
        floor.setNumberOfFloor(floorNumber);
        floorNumber++;
        for (int i = 0; i < valueOfFlats; i++) {
            floor.addFlat(createRandomFlat());
        }
        return floor;
    }
    public Floor createFloor(ArrayList<Integer> squareOfFLats){
        Floor floor = new Floor();
        floor.setNumberOfFloor(floorNumber);
        floorNumber++;
        for (int i = 0; i < valueOfFlats; i++) {
            floor.addFlat(createFlat(squareOfFLats.get(i)));
        }
        return floor;
    }

    public House createRandomHouse() {
        House house = new House();
        for (int i = 0; i < valueOfFloors; i++) {
            house.addFloor(createRandomFloor());
        }
        flatNumber = 0;
        floorNumber = 1;
        return house;
    }

    public House createHouse(ArrayList<Integer> squareOfFlats) {
        House house = new House();
        for (int i = 0; i < valueOfFloors; i++) {
            house.addFloor(createFloor(squareOfFlats));
        }
        squareOfFlats.clear();
        flatNumber = 0;
        floorNumber = 1;
        return house;
    }


}
