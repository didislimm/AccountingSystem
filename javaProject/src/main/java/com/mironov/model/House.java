package com.mironov.model;

import com.mironov.util.HouseGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class House {
    private int id;
    private List<Floor> floors = new ArrayList<>();
    private int numberOfHouse;

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
                floorList.add(HouseGenerator.createRandomFloor(squareOfFlats));
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
