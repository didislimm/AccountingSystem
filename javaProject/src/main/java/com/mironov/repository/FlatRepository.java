package com.mironov.repository;

import com.mironov.model.Flat;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface FlatRepository  {

    void add(final Flat entity,int key) throws SQLException;

    Optional<Flat> getByKey(final Integer key,int numberOfHouse) ;

    void removeByKey(final Integer key) ;

}
