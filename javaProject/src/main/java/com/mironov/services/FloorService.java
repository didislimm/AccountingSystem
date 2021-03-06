package com.mironov.services;

import com.mironov.model.Floor;
import java.util.Objects;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class FloorService {

    private static FloorService Instance = null;


    private FloorService() {
    }

    public static synchronized FloorService getInstance() {
        if (Objects.isNull(Instance)) {
            Instance = new FloorService();
        }
        return Instance;
    }

    public double getCountingOfSquare(Floor floor) {
        return floor.getFlats()
                .stream()
                .flatMapToDouble(flat -> DoubleStream.of(flat.getSquareOfFlat()))
                .sum();
    }

    public int getCountingOfLodger(Floor floor) {
        return floor.getFlats()
                .stream()
                .flatMapToInt(flat -> IntStream.of(flat.getNumberOfLodger()))
                .sum();
    }

    public int getCountingOfRooms(Floor floor) {
        return floor.getFlats()
                .stream()
                .flatMapToInt(flat -> IntStream.of(flat.getNumberOfRoom()))
                .sum();
    }

    public int getCountingOfFlatsInFloor(Floor floor) {
        return floor.getFlats().size();
    }
}
