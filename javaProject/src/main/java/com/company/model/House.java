package com.company.model;

import com.company.services.HouseService;
import com.company.util.HouseGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.List;

@Getter
@Setter
public class House {
    private List<Floor> floors = new ArrayList<>();
    private int numberOfHouse;

    @Override
    public String toString() {
        StringBuilder floorsToString = new StringBuilder();
        HouseService houseService = new HouseService();
        for (Floor floor : floors) {
            floorsToString.append(floor.toString());
        }
        return "House " + numberOfHouse + System.lineSeparator() +
                "Total Area: " + houseService.getTotalAreaOfHouse(numberOfHouse) + System.lineSeparator() +
                "Total value of Lodgers: " + houseService.getTotalLodgersOfHouse(numberOfHouse) + System.lineSeparator() +
                " floors:" + System.lineSeparator() + floorsToString;
    }

    public static final class HouseBuilder {
        private List<Floor> floors = new ArrayList<>();
        private int numberOfHouse;

        private HouseBuilder() {
        }

        public static HouseBuilder aHouse() {
            return new HouseBuilder();
        }

        public HouseBuilder withFloors(List<Floor> floors) {
            this.floors = floors;
            return this;
        }

        public HouseBuilder withNumberOfHouse(int numberOfHouse) {
            this.numberOfHouse = numberOfHouse;
            return this;
        }

        public HouseBuilder but(ArrayList<Integer> squareOfFlats, int numberOfFloors) {
            List<Floor> floorList = new ArrayList<>();
            for (int i = 0; i < numberOfFloors; i++) {
                floorList.add(HouseGenerator.createFloor(squareOfFlats));
            }
            return aHouse().withFloors(floorList).withNumberOfHouse(numberOfHouse);
        }

        public House build() {
            House house = new House();
            house.setFloors(floors);
            house.setNumberOfHouse(numberOfHouse);
            return house;
        }
    }
}
