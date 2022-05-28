package com.mironov.model;

import com.mironov.util.HouseGenerator;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


public class Floor {
    private List<Flat> flats = new ArrayList<>();
    private int numberOfFloor;
    private int numberOfFlats;
    private int numberOfHouse;

    public List<Flat> getFlats() {
        return flats;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public int getNumberOfFlats() {
        return numberOfFlats;
    }

    public void setNumberOfFlats(int numberOfFlats) {
        this.numberOfFlats = numberOfFlats;
    }

    public int getNumberOfHouse() {
        return numberOfHouse;
    }

    public void setNumberOfHouse(int numberOfHouse) {
        this.numberOfHouse = numberOfHouse;
    }

    @Override
    public String toString() {
        StringBuilder flatsToString = new StringBuilder();
        for (Flat flat : flats) {
            flatsToString.append(flat.toString());
        }
        return numberOfFloor + " Floor " +
                " flats: " + flatsToString;
    }

//    public void setFlats(List<Flat> flats) {
//        this.flats.clear();
//        this.flats.addAll(flats);
//    }


    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }

    public static final class FloorBuilder {
        private List<Flat> flats = new ArrayList<>();
        private int numberOfFloor;
        private int numberOfFlatsInFloor;
        private int numberOfHouse;

        private FloorBuilder() {
        }

        public static FloorBuilder aFloor() {
            return new FloorBuilder();
        }

        public FloorBuilder withFlats(List<Flat> flats) {
            this.flats = flats;
            return this;
        }

        public FloorBuilder withNumberOfFloor(int numberOfFloor) {
            this.numberOfFloor = numberOfFloor;
            return this;
        }

        public FloorBuilder withNumberOfFlats(int numberOfFlatsInFloor) {
            this.numberOfFlatsInFloor = numberOfFlatsInFloor;
            return this;
        }

        public FloorBuilder withNumberOfHouse(int numberOfHouse){
             this.numberOfHouse=numberOfHouse;
             return this;
        }
        public FloorBuilder but(ArrayList<Integer> squareOfFlats, int numberOfFloor) {
            List<Flat> flatList = new ArrayList<>();
            for (Integer squareOfFlat : squareOfFlats) {
                flatList.add(HouseGenerator.createRandomFlat(squareOfFlat));
            }
            return aFloor().withFlats(flatList).withNumberOfFloor(numberOfFloor)
                    .withNumberOfFlats(squareOfFlats.size()).withNumberOfHouse(numberOfHouse)
                    .withNumberOfHouse(numberOfHouse);
        }

        public Floor build() {
            Floor floor = new Floor();
            floor.setFlats(flats);
            floor.setNumberOfFloor(numberOfFloor);
            floor.setNumberOfFlats(numberOfFlatsInFloor);
            floor.setNumberOfHouse(numberOfHouse);
            return floor;
        }
    }
}
