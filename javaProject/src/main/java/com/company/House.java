package com.company;

import java.util.ArrayList;

public class House {
    private ArrayList<Floor> floors=new ArrayList<>();
    private int numberOfHouse;

    @Override
    public String toString() {
        return "House "+ numberOfHouse+
                " floors:" + System.lineSeparator()+floors;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    void addFloor(Floor floor) {
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

    public double totalAreaOfHouse(){
        double totalArea=0;
        for (Floor floor:floors){
            totalArea+=floor.countingOfSquare();
        }
        return totalArea;
    }
    public int totalLodgersOfHouse(){
        int totalLodgers=0;
        for (Floor floor:floors){
            totalLodgers+=floor.countingOfLodger();
        }
        return totalLodgers;
    }

    public static final class HouseBuilder {
        private ArrayList<Floor> floors=new ArrayList<>();
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
