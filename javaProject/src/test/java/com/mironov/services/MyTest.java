//package com.mironov.services;
//
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//
//import java.sql.*;
//
//public class MyTest {
//
//
//    private String sql = """
//            DROP TABLE IF EXISTS HOUSES;
//            CREATE TABLE HOUSES(ID INT PRIMARY KEY, NAME VARCHAR(255));
//            INSERT INTO HOUSES VALUES(1, 'Hello');
//            INSERT INTO HOUSES VALUES(2, 'World');""";
//
//
//    @Test
//    public void tests() throws SQLException {
//
//       Connection connection = DriverManager.getConnection("jdbc:h2:mem:my-db");
//        Statement statement = connection.createStatement();
//        statement.execute(sql);
//
//        PreparedStatement preparedStatement = connection.prepareStatement("select * from HOUSES");
////        preparedStatement.setInt(1,1);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        while (resultSet.next()){
//            String name = resultSet.getString("name");
//        }
//
//
//    }
//}
