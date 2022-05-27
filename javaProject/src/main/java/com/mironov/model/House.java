package com.mironov.model;

import com.mironov.repository.impl.HouseRepositoryImpl;
import com.mironov.services.HouseService;
import com.mironov.util.HouseGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class House {
    private int numberOfHouse;
    private int valueOfFloors;
    private List<Floor> floors = new ArrayList<>();

//    public void setFloors(List<Floor> floors) {
//        this.floors.clear();
//        this.floors.addAll(floors);
//    }


    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        StringBuilder floorsToString = new StringBuilder();
        for (Floor floor : floors) {
            floorsToString.append(floor.toString());
        }
        HouseService houseService=HouseService.getInstance(HouseRepositoryImpl.getInstance());
        return "House " + numberOfHouse + "<p>" +
                "Total Area: " + houseService.getTotalAreaOfHouse(numberOfHouse) + "<p>" +
                "Total value of Lodgers: " + houseService.getTotalLodgersOfHouse(numberOfHouse) + "<p>" +
                " floors:" + "<p>" + floorsToString;
    }


    public static final class HouseBuilder {
        private int numberOfHouse;
        private int numberOfFloors;
        private List<Floor> floors = new ArrayList<>();

        private HouseBuilder() {
        }

        public static HouseBuilder aHouse() {
            return new HouseBuilder();
        }

        public HouseBuilder withNumberOfHouse(int numberOfHouse) {
            this.numberOfHouse = numberOfHouse;
            return this;
        }

        public HouseBuilder withNumberOfFloors() {
            this.numberOfFloors = floors.size();
            return this;
        }

        public HouseBuilder withFloors(List<Floor> floors) {
            this.floors = floors;
            return this;
        }

        public HouseBuilder but(ArrayList<Double> squareOfFlats, int numberOfFloors) {
            List<Floor> floorList = new ArrayList<>();
            for (int i = 0; i < numberOfFloors; i++) {
                floorList.add(HouseGenerator.createRandomFloor(squareOfFlats));
            }
            return aHouse().withFloors(floorList).withNumberOfHouse(numberOfHouse)
                    .withNumberOfFloors();
        }

        public House build() {
            House house = new House();
            house.setNumberOfHouse(numberOfHouse);
            house.setValueOfFloors(numberOfFloors);
            house.setFloors(floors);
            return house;
        }
    }
}
