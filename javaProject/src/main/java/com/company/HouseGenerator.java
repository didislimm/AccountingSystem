package com.company;

import com.company.domain.Flat;
import com.company.domain.Floor;
import com.company.domain.House;

public class HouseGenerator {
    private final int valueOfFlats;
    private final int valueOfFloors;
    private int flatNumber = 0;
    private int floorNumber = 1;
    private int houseNumber = 1;

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

    public Flat createFlat() {
        return Flat.FlatBuilder.aFlat().build();
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

    public House createRandomHouse() {
        House house = new House();
        house.setNumberOfHouse(houseNumber);
        houseNumber++;
        for (int i = 0; i < valueOfFloors; i++) {
            house.addFloor(createRandomFloor());
        }
        flatNumber = 0;
        floorNumber = 1;
        return house;
    }
}
