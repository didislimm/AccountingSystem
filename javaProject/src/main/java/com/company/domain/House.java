package com.company.domain;

import java.util.ArrayList;

public class House {
    private ArrayList<Floor> floors = new ArrayList<>();
    private int numberOfHouse;

    @Override
    public String toString() {
        StringBuilder floorsToString = new StringBuilder();
        for (Floor floor : floors) {
            floorsToString.append(floor.toString());
        }
        return "House " + numberOfHouse + System.lineSeparator() +
                "Total Area: " + totalAreaOfHouse() + System.lineSeparator() +
                "Total value of Lodgers: " + totalLodgersOfHouse() + System.lineSeparator() +
                " floors:" + System.lineSeparator() + floorsToString;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public void addFloor(Floor floor) {
        this.floors.add(floor);
    }

    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }

    public int getNumberOfHouse() {
        return numberOfHouse;
    }

    public void setNumberOfHouse(int numberOfHouse) {
        this.numberOfHouse = numberOfHouse;
    }

    public int getValueOfFLats() {
        int valueOfFlats = 0;
        for (Floor floor : floors) {
            valueOfFlats += floor.countingOfFlats();
        }
        return valueOfFlats;
    }

    public int getNumberOfFloors() {
        return floors.size();
    }

    public Flat getFlat(int numberOfFlat) {
        int numberOfFlatInFlor = floors.get(0).getNumberOfFlatsInFlor();
        int numberOfFlor = getFloorOfFlat(numberOfFlat, numberOfFlatInFlor);
        return floors.get(numberOfFlor).getFlatByNumber(numberOfFlat);
    }

    public int getFloorOfFlat(int numberOfFlat, int numberOfFlatInFlor) {
        int numberOfFloors = getNumberOfFloors();
        return (((numberOfFlat - 1) % (numberOfFloors * numberOfFlatInFlor) / numberOfFlatInFlor) + 1);
    }

    public double totalAreaOfHouse() {
        double totalArea = 0;
        for (Floor floor : floors) {
            totalArea += floor.countingOfSquare();
        }
        return totalArea;
    }

    public int totalLodgersOfHouse() {
        int totalLodgers = 0;
        for (Floor floor : floors) {
            totalLodgers += floor.countingOfLodger();
        }
        return totalLodgers;
    }

    public static final class HouseBuilder {
        private ArrayList<Floor> floors = new ArrayList<>();
        private int numberOfHouse;

        private HouseBuilder() {
        }

        public static HouseBuilder aHouse() {
            return new HouseBuilder();
        }

        public HouseBuilder withFloors(ArrayList<Floor> floors) {
            this.floors = floors;
            return this;
        }

        public HouseBuilder withNumberOfHouse(int numberOfHouse) {
            this.numberOfHouse = numberOfHouse;
            return this;
        }

        public House build() {
            House house = new House();
            house.setFloors(floors);
            house.setNumberOfHouse(numberOfHouse);
            return house;
        }
    }
}
