//package com.mironov.services;
//
//import com.mironov.model.Flat;
//import com.mironov.model.Floor;
//import com.mironov.util.SingletonFactory;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class FloorServiceTest {
//
//    FloorService floorService = SingletonFactory.getObject(FloorService.class);
//    static Floor floor=new Floor();
//    @BeforeAll
//      static void beforeAll() {
//        for (int i = 0; i < 5; i++) {
//            floor.getFlats().add(Flat.FlatBuilder
//                    .aFlat()
//                    .withNumberOfFlat(i + 1)
//                    .withNumberOfLodger(4)
//                    .withNumberOfRoom(i + 1)
//                    .withSquareOfFlat(i + 15)
//                    .build());
//        }
//    }
//    @Test
//     void getCountingOfSquareTest() {
//        assertEquals(85,floorService.getCountingOfSquare(floor));
//    }
//
//    @Test
//    void getCountingOfLodgerTest() {
//        assertEquals(20, floorService.getCountingOfLodger(floor));
//    }
//
//    @Test
//    void getCountingOfRoomsTest() {
//        assertEquals(15, floorService.getCountingOfRooms(floor));
//    }
//
//    @Test
//    void getCountingOfFlatsInFloorTest(){
//        assertEquals(5,floorService.getCountingOfFlatsInFloor(floor));
//    }
//
//}