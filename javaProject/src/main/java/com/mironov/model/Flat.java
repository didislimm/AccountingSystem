package com.mironov.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Flat {
    private int numberOfFlat;
    private double squareOfFlat;
    private int numberOfLodger;
    private int numberOfRoom;
    private int numberOfFloor;
    private int numberOfHouse;


    public int getNumberOfFlat() {
        return numberOfFlat;
    }

    public void setNumberOfFlat(int numberOfFlat) {
        this.numberOfFlat = numberOfFlat;
    }

    public double getSquareOfFlat() {
        return squareOfFlat;
    }

    public void setSquareOfFlat(double squareOfFlat) {
        this.squareOfFlat = squareOfFlat;
    }

    public int getNumberOfLodger() {
        return numberOfLodger;
    }

    public void setNumberOfLodger(int numberOfLodger) {
        this.numberOfLodger = numberOfLodger;
    }

    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(int numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public int getNumberOfHouse() {
        return numberOfHouse;
    }

    public void setNumberOfHouse(int numberOfHouse) {
        this.numberOfHouse = numberOfHouse;
    }

    @Override
    public String toString() {
        return System.lineSeparator() + "Flat " + numberOfFlat +
                " squareOfFlat=" + squareOfFlat +
                " numberOfPeople=" + numberOfLodger +
                " numberOfRoom=" + numberOfRoom + '\n';
    }

    public static final class FlatBuilder {
        private int numberOfFlat;
        private double squareOfFlat;
        private int numberOfLodger;
        private int numberOfRoom;
        private int numberOfFloor;
        private int numberOfHouse;

        private FlatBuilder() {
        }

        public static FlatBuilder aFlat() {
            return new FlatBuilder();
        }

        public FlatBuilder withNumberOfFlat(int numberOfFlat) {
            this.numberOfFlat = numberOfFlat;
            return this;
        }

        public FlatBuilder withSquareOfFlat(double squareOfFlat) {
            this.squareOfFlat = squareOfFlat;
            return this;
        }

        public FlatBuilder withNumberOfLodger(int numberOfLodger) {
            this.numberOfLodger = numberOfLodger;
            return this;
        }

        public FlatBuilder withNumberOfRoom(int numberOfRoom) {
            this.numberOfRoom = numberOfRoom;
            return this;
        }

        public FlatBuilder withNumberOfFloor(int numberOfFloor) {
            this.numberOfFloor = numberOfFloor;
            return this;
        }

        public FlatBuilder withNumberOfHouse(int numberOfHouse) {
            this.numberOfHouse = numberOfHouse;
            return this;
        }

        public Flat build() {
            Flat flat = new Flat();
            flat.setNumberOfFlat(numberOfFlat);
            flat.setSquareOfFlat(squareOfFlat);
            flat.setNumberOfLodger(numberOfLodger);
            flat.setNumberOfRoom(numberOfRoom);
            flat.setNumberOfFloor(numberOfFloor);
            flat.setNumberOfHouse(numberOfHouse);
            return flat;
        }
    }
}


