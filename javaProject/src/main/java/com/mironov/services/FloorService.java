package com.mironov.services;

import com.mironov.model.*;

public class FloorService {

    public double getCountingOfSquare(Floor floor) {
        double totalArea = 0;
        for (Flat flat : floor.getFlats()) {
            totalArea += flat.getSquareOfFlat();
        }
        return totalArea;
    }

    public int getCountingOfLodger(Floor floor) {
        int totalLodger = 0;
        for (Flat flat : floor.getFlats()) {
            totalLodger += flat.getNumberOfLodger();
        }
        return totalLodger;
    }

    public int getCountingOfRooms(Floor floor) {
        int totalRooms = 0;
        for (Flat flat : floor.getFlats()) {
            totalRooms += flat.getNumberOfRoom();
        }
        return totalRooms;
    }

    public int getCountingOfFlatsInFloor(Floor floor) {
        return floor.getFlats().size();
    }
}
