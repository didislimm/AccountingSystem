package com.mironov.services;

import com.mironov.model.Floor;
import com.mironov.repository.FloorRepository;

import java.util.Objects;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class FloorService {

    private static FloorService instance = null;

    private final FloorRepository floorRepository;

    private FloorService(final FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    public static synchronized FloorService getInstance(final FloorRepository floorRepository) {
        if (Objects.isNull(instance)) {
            instance = new FloorService(floorRepository);
        }
        return instance;
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
