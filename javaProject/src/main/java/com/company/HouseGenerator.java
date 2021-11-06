package com.company;

public class HouseGenerator {
    private final int valueOfFlats;
    private final int valueOfFloors;
    private int flatNumber = 0;
    private int floorNumber = 1;
    private int houseNumber = 1;

    public HouseGenerator(int valueOfFlats, int valueOfFloors) {
        this.valueOfFlats = valueOfFlats;
        this.valueOfFloors = valueOfFloors;
    }

    public int getValueOfFlats() {
        return valueOfFlats;
    }

    public int getValueOfFloors() {
        return valueOfFloors;
    }

    public static double rnd(int min, int max) {
        max -= min;
        return (Math.random() * ++max) + min;
    }

    Flat randomFlatCreator() {
        double randomSquareOfFlat = rnd(40, 130);
        int randomNumberOfRoom = (int) rnd(1, 5);
        int randomNumberOfLodger = (int) rnd(1, 6);
        flatNumber++;
        return Flat.FlatBuilder.aFlat()
                .withSquareOfFlat(randomSquareOfFlat)
                .withNumberOfRoom(randomNumberOfRoom)
                .withNumberOfLodger(randomNumberOfLodger)
                .withNumberOfFlat(flatNumber)
                .build();
    }

    Floor randomFloorCreator() {
        Floor floor = new Floor();
        floor.setNumberOfFloor(floorNumber);
        floorNumber++;
        for (int i = 0; i < valueOfFlats; i++) {
            floor.addFlat(randomFlatCreator());
        }
        return floor;
    }
    House randomHouseCreator(){
        House house= new House();
        house.setNumberOfHouse(houseNumber);
        houseNumber++;
        for (int i=0;i<valueOfFloors;i++){
            house.addFloor(randomFloorCreator());
        }
        flatNumber=0;
        floorNumber=1;
        return house;
    }
}
