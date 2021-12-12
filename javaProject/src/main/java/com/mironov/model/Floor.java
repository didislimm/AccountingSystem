package com.mironov.model;

import com.mironov.util.HouseGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Floor {
    private List<Flat> flats = new ArrayList<>();
    private int numberOfFloor;
    private int numberOfFlatsInFloor;

    @Override
    public String toString() {
        StringBuilder flatsToString = new StringBuilder();
        for (Flat flat : flats) {
            flatsToString.append(flat.toString());
        }
        return numberOfFloor + " Floor " +
                " flats: " + flatsToString;
    }

    public static final class FloorBuilder {
        private int id;
        private List<Flat> flats = new ArrayList<>();
        private int numberOfFloor;
        private int numberOfFlatsInFlor;

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

        public FloorBuilder withNumberOfFlatsInFlor(int numberOfFlatsInFlor) {
            this.numberOfFlatsInFlor = numberOfFlatsInFlor;
            return this;
        }

        public FloorBuilder but(ArrayList<Integer> squareOfFlats, int numberOfFloor) {
            List<Flat> flatList = new ArrayList<>();
            for (Integer squareOfFlat : squareOfFlats) {
                flatList.add(HouseGenerator.createRandomFlat(squareOfFlat));
            }
            return aFloor().withFlats(flatList).withNumberOfFloor(numberOfFloor).withNumberOfFlatsInFlor(squareOfFlats.size());
        }

        public Floor build() {
            Floor floor = new Floor();
            floor.setFlats(flats);
            floor.setNumberOfFloor(numberOfFloor);
            floor.setNumberOfFlatsInFloor(numberOfFlatsInFlor);
            return floor;
        }
    }
}
