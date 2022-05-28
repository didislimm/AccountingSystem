package com.mironov.repository.impl;

import com.mironov.dataBase.ConnectionPool;
import com.mironov.dataBase.Const;
import com.mironov.model.Flat;
import com.mironov.model.Floor;
import com.mironov.model.House;
import com.mironov.repository.HouseRepository;

import javax.sound.midi.Soundbank;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//         5 Запрос c UNION +-
//         6 2 запроса с подзапросами, один взаимосвязанный, второй - нет.-+

public class HouseRepositoryImpl implements HouseRepository {
    private static HouseRepository instance = null;
    private final FlatRepositoryImpl flatRepository = new FlatRepositoryImpl();
    private final ConnectionPool connectionPool = new ConnectionPool(10);

    private HouseRepositoryImpl() {
    }

    public synchronized static HouseRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new HouseRepositoryImpl();
        }
        return instance;
    }


    public void add(final House house) throws SQLException {
        String insertInHouse = "INSERT INTO " + Const.HOUSE_TABLE + "(" +
                Const.HOUSE_NUMBER_OF_HOUSE + "," + Const.HOUSE_VALUES_OF_FLOORS + ")"
                + "VALUES (?,?)";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertInHouse);
            preparedStatement.setInt(1, house.getNumberOfHouse());
            preparedStatement.setInt(2, house.getValueOfFloors());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String houseId = "SELECT " + Const.HOUSE_ID + " FROM " + Const.HOUSE_TABLE + " WHERE " + Const.HOUSE_NUMBER_OF_HOUSE
                + "=" + house.getNumberOfHouse();
        int id = 0;
        try {
            preparedStatement = connection.prepareStatement(houseId);
            ResultSet resultSet = preparedStatement.executeQuery(houseId);
            resultSet.next();
            id = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.returnConnection(connection);
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        for (Floor floor : house.getFloors()) {
            for (Flat flat : floor.getFlats()) {
                flat.setNumberOfHouse(house.getNumberOfHouse());
                flat.setNumberOfFloor(floor.getNumberOfFloor());
                flatRepository.add(flat, id);
            }
        }
    }

    public Optional<House> getByKey(Integer numberOfHouse) {

        House house = new House();
        try {
            String find = "SELECT " + Const.HOUSE_VALUES_OF_FLOORS + "," +
                    Const.FLATS_VALUE_OF_LODGERS + "," + Const.FLATS_VALUE_OF_ROOMS + "," +
                    Const.FLATS_NUMBER_OF_FLAT + "," + Const.FLATS_NUMBER_OF_FLOOR + "," + Const.FLATS_NUMBER_OF_HOUSE + ","
                    + Const.FLATS_SQUARE + " FROM " + Const.FLAT_TABLE + " INNER JOIN " +
                    Const.HOUSE_TABLE + " ON " + Const.FLATS_HOUSE_ID + "=" + Const.HOUSE_ID + " WHERE "
                    + Const.FLATS_NUMBER_OF_HOUSE + "=" + numberOfHouse + " ORDER BY " + Const.FLATS_NUMBER_OF_FLAT;


            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(find,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = preparedStatement.executeQuery(find);
            resultSet.last();
            int valueOfFlats = resultSet.getRow();
            resultSet.first();
            int valueOfFloors = resultSet.getInt(1);
            int valueOfFlatsInFloor = valueOfFlats / valueOfFloors;
            List<Floor> floors = new ArrayList<>();
            for (int numberOfFloor = 1; numberOfFloor <= valueOfFloors; numberOfFloor++) {
                Floor floor = new Floor();
                floor.setNumberOfFloor(numberOfFloor);
                floor.setNumberOfHouse(numberOfHouse);
                floor.setNumberOfFlats(valueOfFlatsInFloor);

                List<Flat> flats = new ArrayList<>();
                for (int i = 1; i <= valueOfFlatsInFloor; i++) {
                    Flat flat = new Flat();
                    flat.setNumberOfLodger(resultSet.getInt(2));
                    flat.setNumberOfRoom(resultSet.getInt(3));
                    flat.setNumberOfFlat(resultSet.getInt(4));
                    flat.setNumberOfFloor(resultSet.getInt(5));
                    flat.setNumberOfHouse(resultSet.getInt(6));
                    flat.setSquareOfFlat(resultSet.getDouble(7));
                    flats.add(flat);
                    resultSet.next();
                }
                floor.setFlats(flats);
                floors.add(floor);
            }
            house.setNumberOfHouse(numberOfHouse);
            house.setFloors(floors);
            house.setValueOfFloors(valueOfFloors);
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(house);
    }

    public List<House> getAll() {
        return null;
    }

    public List<Integer> getAllKey() {
        String find = "SELECT " + Const.HOUSE_NUMBER_OF_HOUSE + " FROM " + Const.HOUSE_TABLE + " ORDER BY "
                + Const.HOUSE_NUMBER_OF_HOUSE;
        List<Integer> numbersOfHouses = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(find);
            ResultSet resultSet = preparedStatement.executeQuery(find);
            while (resultSet.next()) {
                numbersOfHouses.add(resultSet.getInt(1));
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numbersOfHouses;
    }

    public void removeByKey(Integer key) {
        PreparedStatement preparedStatement;
        Connection connection = null;
        String houseId = "SELECT " + Const.HOUSE_ID + " FROM " + Const.HOUSE_TABLE + " WHERE " + Const.HOUSE_NUMBER_OF_HOUSE
                + "=" + key;
        int id = 0;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(houseId);
            ResultSet resultSet = preparedStatement.executeQuery(houseId);
            resultSet.next();
            id = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        flatRepository.removeByKey(id);

        String find = "DELETE FROM " + Const.HOUSE_TABLE + " WHERE " + Const.HOUSE_NUMBER_OF_HOUSE + "=" + key;
        try {
            preparedStatement = connection.prepareStatement(find);
            preparedStatement.executeUpdate(find);
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getMainOfHouse(final Integer key) {
        String find = "SELECT sum("+Const.FLATS_SQUARE+") as sumSquare,sum("+Const.FLATS_VALUE_OF_LODGERS+") as sumLodgers"+
                " FROM "+Const.FLAT_TABLE+" Where "+Const.FLATS_NUMBER_OF_HOUSE+"="+key+" Union "+
                "SELECT (select count("+Const.FLATS_SQUARE+") FROM "+Const.FLAT_TABLE+" Where "
                +Const.FLATS_NUMBER_OF_HOUSE+"="+key+"),"+Const.HOUSE_NUMBER_OF_HOUSE+" FROM "+
                Const.HOUSE_TABLE+" Where "+Const.HOUSE_NUMBER_OF_HOUSE+"="+key;
        PreparedStatement preparedStatement;
        Connection connection;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(find);
            ResultSet resultSet=preparedStatement.executeQuery(find);
            resultSet.next();
            double sumSquare=resultSet.getDouble(1);
            int sumLodgers=resultSet.getInt(2);
            resultSet.next();
            int valueOfFlats=resultSet.getInt(1);
            int numberOfHouse=resultSet.getInt(2);
            System.out.println(
                    "Number of House:"+numberOfHouse
                    +"Sum square:"+sumSquare
                    +"Sum lodgers:"+sumLodgers
                    +"Value of flats:"+valueOfFlats);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getHousesWithSquare(final Double square) {
        String find = "SELECT " + Const.FLATS_NUMBER_OF_HOUSE +","+"SUM(" + Const.FLATS_SQUARE + ")" + " FROM " + Const.FLAT_TABLE
                + " GROUP BY " + Const.FLATS_NUMBER_OF_HOUSE + " HAVING " + "SUM(" + Const.FLATS_SQUARE + ")" + ">=" + square;
        PreparedStatement preparedStatement;
        Connection connection;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(find);
            ResultSet resultSet = preparedStatement.executeQuery(find);
            while (resultSet.next()) {
                System.out.println("In house with number" + resultSet.getInt(1) + " .With square:"
                        + resultSet.getDouble(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isFlatInNormal(final int numberOfFlat, final int numberOfHouse) {
        double livingSpaceForFlat = 10.0;
        String find = "SELECT ceiling((SELECT " + Const.FLATS_SQUARE + " FROM " + Const.FLAT_TABLE + " WHERE "
                + Const.FLATS_NUMBER_OF_HOUSE + "=" + numberOfHouse + " AND " + Const.FLATS_NUMBER_OF_FLAT + "=" +
                numberOfFlat + ")" + "/(SELECT " + Const.FLATS_VALUE_OF_LODGERS + " FROM " + Const.FLAT_TABLE + " WHERE " +
                Const.FLATS_NUMBER_OF_HOUSE + "=" + numberOfHouse + " AND " + Const.FLATS_NUMBER_OF_FLAT + "="
                + numberOfFlat + "))"+" As normalForm";
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(find);
            ResultSet resultSet = preparedStatement.executeQuery(find);
            resultSet.next();
            if (resultSet.getDouble(1) >= livingSpaceForFlat) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getValueOfFreeFlats(int numberOfHouse) {
        String find = "SELECT sum(case WHEN "+Const.FLATS_VALUE_OF_LODGERS+"=0 "+"Then 1 Else 0 end) as counter"+
                " FROM "+Const.FLAT_TABLE+" WHERE "+Const.FLATS_NUMBER_OF_HOUSE+"="+numberOfHouse;
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection=connectionPool.getConnection();
            preparedStatement=connection.prepareStatement(find);
            ResultSet resultSet=preparedStatement.executeQuery(find);
            resultSet.next();
            int valueOfFlats=resultSet.getInt(1);
            connectionPool.returnConnection(connection);
            return valueOfFlats;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
