package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {

    @Test
    void totalAreaOfHouse() {
        House house = new House();
        Floor floor1 = new Floor();
        floor1.addFlat(Flat.FlatBuilder.aFlat().withSquareOfFlat(5).build());
        floor1.addFlat(Flat.FlatBuilder.aFlat().withSquareOfFlat(15).build());
        house.addFloor(floor1);
        Floor floor2 = new Floor();
        floor2.addFlat(Flat.FlatBuilder.aFlat().withSquareOfFlat(25).build());
        floor2.addFlat(Flat.FlatBuilder.aFlat().withSquareOfFlat(35).build());
        house.addFloor(floor2);
        Floor floor3 = new Floor();
        floor3.addFlat(Flat.FlatBuilder.aFlat().withSquareOfFlat(45).build());
        floor3.addFlat(Flat.FlatBuilder.aFlat().withSquareOfFlat(25).build());
        house.addFloor(floor3);
        double totalArea = house.totalAreaOfHouse();
        assertEquals(150,totalArea);
    }

    @Test
    void totalLodgersOfHouse() {
        House house = new House();
        Floor floor1 = new Floor();
        floor1.addFlat(Flat.FlatBuilder.aFlat().withNumberOfLodger(1).build());
        floor1.addFlat(Flat.FlatBuilder.aFlat().withNumberOfLodger(2).build());
        house.addFloor(floor1);
        Floor floor2 = new Floor();
        floor2.addFlat(Flat.FlatBuilder.aFlat().withNumberOfLodger(3).build());
        floor2.addFlat(Flat.FlatBuilder.aFlat().withNumberOfLodger(4).build());
        house.addFloor(floor2);
        Floor floor3 = new Floor();
        floor3.addFlat(Flat.FlatBuilder.aFlat().withNumberOfLodger(5).build());
        floor3.addFlat(Flat.FlatBuilder.aFlat().withNumberOfLodger(15).build());
        house.addFloor(floor3);
        int totalLodgers = house.totalLodgersOfHouse();
        assertEquals(30,totalLodgers);
    }
}