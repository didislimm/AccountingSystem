package com.mironov.repository.impl;

import com.mironov.dataBase.ConnectionPool;
import com.mironov.dataBase.Const;
import com.mironov.model.Flat;
import com.mironov.repository.FlatRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlatRepositoryImpl implements FlatRepository {
    private final ConnectionPool connectionPool=new ConnectionPool(10);

    protected FlatRepositoryImpl() {
    }

    @Override
    public void add(Flat entity,int houseId) throws SQLException {
        String insert = "INSERT INTO " + Const.FLAT_TABLE + "(" +
                Const.FLATS_SQUARE + "," +
                Const.FLATS_VALUE_OF_LODGERS + "," + Const.FLATS_VALUE_OF_ROOMS + "," +
                Const.FLATS_NUMBER_OF_FLAT + "," + Const.FLATS_NUMBER_OF_FLOOR + "," + Const.FLATS_NUMBER_OF_HOUSE+","
                +Const.FLATS_HOUSE_ID+ ")" + "VALUES(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        Connection connection;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setDouble(1, entity.getSquareOfFlat());
            preparedStatement.setInt(2, entity.getNumberOfLodger());
            preparedStatement.setInt(3, entity.getNumberOfRoom());
            preparedStatement.setInt(4, entity.getNumberOfFlat());
            preparedStatement.setInt(5, entity.getNumberOfFloor());
            preparedStatement.setInt(6, entity.getNumberOfHouse());
            preparedStatement.setInt(7,houseId);
            preparedStatement.executeUpdate();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        @Override
        public Optional<Flat> getByKey (Integer key,int numberOfHouse){
            String find = "SELECT " + Const.FLATS_SQUARE + "," + Const.FLATS_VALUE_OF_LODGERS + ","
                    + Const.FLATS_VALUE_OF_ROOMS + "," + Const.FLATS_NUMBER_OF_FLAT + " FROM " + Const.FLAT_TABLE +
                    " WHERE " + Const.FLATS_NUMBER_OF_FLAT + "="
                    + key + " AND " + Const.FLATS_NUMBER_OF_HOUSE + "=" + numberOfHouse;
            Flat result = new Flat();
            try {
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(find);
                ResultSet flat = preparedStatement.executeQuery(find);
                result.setSquareOfFlat(flat.getDouble(1));
                result.setNumberOfLodger(flat.getInt(2));
                result.setNumberOfRoom(flat.getInt(3));
                result.setNumberOfFlat(flat.getInt(4));
                result.setNumberOfHouse(flat.getInt(numberOfHouse));
                connectionPool.returnConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.of(result);
        }

        @Override
        public void removeByKey (Integer houseId){
            String find = "DELETE FROM " + Const.FLAT_TABLE +
                    " WHERE " + Const.FLATS_HOUSE_ID + "="+ houseId;
            try {
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(find);
                preparedStatement.executeUpdate(find);
                connectionPool.returnConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
