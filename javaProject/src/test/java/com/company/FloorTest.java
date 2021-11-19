//package com.company;
//
//import com.company.model.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
////TODO билдеры +
//class FloorTest {
//
//@BeforeAll
//    void need(){
//    Floor floor=
//
//    }
//
//    @Test
//    void countingOfSquareTest() {
//        Floor floor = new Floor();
//        floor.addFlat(Flat.FlatBuilder.aFlat().withSquareOfFlat(5).build());
//        floor.addFlat(Flat.FlatBuilder.aFlat().withSquareOfFlat(15).build());
//        floor.addFlat(Flat.FlatBuilder.aFlat().withSquareOfFlat(25).build());
//        floor.addFlat(Flat.FlatBuilder.aFlat().withSquareOfFlat(35).build());
//        floor.addFlat(Flat.FlatBuilder.aFlat().withSquareOfFlat(45).build());
//        double totalArea = floor.countingOfSquare();
//        assertEquals(125, totalArea);
//    }
//
//    @Test
//    void countingOfLodgerTest() {
//        Floor floor = new Floor();
//        floor.addFlat(Flat.FlatBuilder.aFlat().withNumberOfLodger(5).build());
//        floor.addFlat(Flat.FlatBuilder.aFlat().withNumberOfLodger(4).build());
//        floor.addFlat(Flat.FlatBuilder.aFlat().withNumberOfLodger(3).build());
//        floor.addFlat(Flat.FlatBuilder.aFlat().withNumberOfLodger(2).build());
//        floor.addFlat(Flat.FlatBuilder.aFlat().withNumberOfLodger(1).build());
//        double totalLodger = floor.countingOfLodger();
//        assertEquals(15, totalLodger);
//    }
//
//}