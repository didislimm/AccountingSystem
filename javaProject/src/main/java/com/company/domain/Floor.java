package com.company.domain;

import java.util.ArrayList;

public class Floor {
    private ArrayList<Flat> flats = new ArrayList<>();
    private int numberOfFloor;


    public void setFlats(ArrayList<Flat> flats) {
        this.flats = flats;
    }

    public void addFlat(Flat flat) {
        this.flats.add(flat);
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    @Override
    public String toString() {
        return numberOfFloor + " Floor " +
                " flats= " + flats;
    }

    public double countingOfSquare() {
        double totalArea = 0;
        for (Flat flat : flats) {
            totalArea += flat.getSquareOfFlat();
        }
        return totalArea;
    }

    public int countingOfLodger() {
        int totalLodger = 0;
        for (Flat flat : flats) {
            totalLodger += flat.getNumberOfLodger();
        }
        return totalLodger;
    }


    public static final class FloorBuilder {
        private ArrayList<Flat> flats = new ArrayList<>();
        private int numberOfFloor;

        private FloorBuilder() {
        }

        public static FloorBuilder aFloor() {
            return new FloorBuilder();
        }

        public FloorBuilder withFlats(ArrayList<Flat> flats) {
            this.flats = flats;
            return this;
        }

        public FloorBuilder withNumberOfFloor(int numberOfFloor) {
            this.numberOfFloor = numberOfFloor;
            return this;
        }

        public Floor build() {
            Floor floor = new Floor();
            floor.setFlats(flats);
            floor.numberOfFloor = this.numberOfFloor;
            return floor;
        }
    }
}
