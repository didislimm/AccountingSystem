//package com.mironov;
//
//import com.mironov.dataBase.ConnectionPool;
//import org.apache.ibatis.jdbc.ScriptRunner;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class BaseTest {
//
//    public BaseTest(){
//        ConnectionPool connectionPool=new ConnectionPool(5);
//        Connection connection = null;
//        try {
//            connection = connectionPool.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        ScriptRunner scriptRunner = new ScriptRunner(connection);
//        try {
//            scriptRunner.runScript(new BufferedReader(new FileReader("db-init-script.sql")));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}
