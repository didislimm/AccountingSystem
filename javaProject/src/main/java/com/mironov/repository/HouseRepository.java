package com.mironov.repository;

import com.mironov.model.House;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface HouseRepository  {
     void add(final House entity) throws SQLException;

    Optional<House> getByKey(final Integer key) ;

    List<Integer> getAllKey();

    void removeByKey(final Integer key) ;

    void getMainOfHouse(final Integer key);

    void getHousesWithSquare(final Double square);

    boolean isFlatInNormal(final int numberOfFlat, final int numberOfHouse);

    int getValueOfFreeFlats(int numberOfHouse);
}
