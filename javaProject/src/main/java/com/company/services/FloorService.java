package com.company.services;

import com.company.model.*;

public class FloorService {

    public double countingOfSquare(Floor floor) {
        double totalArea = 0;
        for (Flat flat : floor.getFlats()) {
            totalArea += flat.getSquareOfFlat();
        }
        return totalArea;
    }

    public int countingOfLodger(Floor floor) {
        int totalLodger = 0;
        for (Flat flat : floor.getFlats()) {
            totalLodger += flat.getNumberOfLodger();
        }
        return totalLodger;
    }

    public int countingOfFlats(Floor floor) {
        return floor.getNumberOfFlatsInFlor();
    }

}
