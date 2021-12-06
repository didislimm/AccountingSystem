package com.mironov.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Flat {
    private double squareOfFlat;
    private int numberOfLodger;
    private int numberOfRoom;
    private int numberOfFlat;

    @Override
    public String toString() {
        return System.lineSeparator() + "Flat " + numberOfFlat +
                " squareOfFlat=" + squareOfFlat +
                " numberOfPeople=" + numberOfLodger +
                " numberOfRoom=" + numberOfRoom + '\n';
    }

    public static final class FlatBuilder {
        private double squareOfFlat;
        private int numberOfLodger;
        private int numberOfRoom;
        private int numberOfFlat;

        private FlatBuilder() {
        }

        public static FlatBuilder aFlat() {
            return new FlatBuilder();
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

        public FlatBuilder withNumberOfFlat(int numberOfFlat) {
            this.numberOfFlat = numberOfFlat;
            return this;
        }

        public Flat build() {
            Flat flat = new Flat();
            flat.setSquareOfFlat(squareOfFlat);
            flat.setNumberOfLodger(numberOfLodger);
            flat.setNumberOfRoom(numberOfRoom);
            flat.setNumberOfFlat(numberOfFlat);
            return flat;
        }
    }
}


