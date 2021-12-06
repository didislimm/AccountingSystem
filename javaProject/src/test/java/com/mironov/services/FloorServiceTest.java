package com.mironov.services;

import com.mironov.model.Flat;
import com.mironov.model.Floor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloorServiceTest {

    FloorService floorService = new FloorService();
    static Floor floor=new Floor();
    @BeforeAll
      static void beforeAll() {
        for (int i = 0; i < 5; i++) {
            floor.getFlats().add(Flat.FlatBuilder
                    .aFlat()
                    .withNumberOfFlat(i + 1)
                    .withNumberOfLodger(4)
                    .withNumberOfRoom(i + 1)
                    .withSquareOfFlat(i + 15)
                    .build());
        }
    }
    @Test
     void getCountingOfSquareTest() {
        double totalArea = floorService.getCountingOfSquare(floor);
        assertEquals(85, totalArea);
    }

    @Test
    void getCountingOfLodgerTest() {
        double totalValueOfLodgers = floorService.getCountingOfLodger(floor);
        assertEquals(20, totalValueOfLodgers);
    }

    @Test
    void getCountingOfRoomsTest() {
        double totalValueOfRooms = floorService.getCountingOfRooms(floor);
        assertEquals(15, totalValueOfRooms);
    }

}