package com.mironov.dataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;

public class ConnectionPool {
    private static final String SQL_VERIFY_CONN = "select 1";
    private final String dataBaseUrl;
    private final String userName;
    private final String password;
    private final int maxPoolSize;
    Stack<Connection> freePool = new Stack<>();
    Set<Connection> occupiedPool = new HashSet<>();
    private int valueOfConnections = 0;

    public ConnectionPool(int maxPoolSize) {
        FileInputStream fis;
        Properties properties = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/db.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dataBaseUrl = properties.getProperty("db.url");
        this.userName = properties.getProperty("db.user-name");
        this.password = properties.getProperty("db.password");
        this.maxPoolSize = maxPoolSize;
    }

    public synchronized Connection getConnection() throws SQLException {
        Connection connection = null;
        if (isFull()) {
            throw new SQLException("Connection Pool is full");
        }
        connection = getConnectionFromPool();
        if (connection == null) {
            connection = createNewConnection();
        }
        connection = makeAvailable(connection);
        return connection;
    }

    public synchronized void returnConnection(Connection connection) throws SQLException {
        if (connection == null) {
            throw new NullPointerException();
        }
        if (!occupiedPool.remove(connection)) {
            throw new SQLException("The connection is returned already");
        }
        freePool.push(connection);
    }

    private synchronized boolean isFull() {
        return (freePool.size() == 0 && valueOfConnections >= maxPoolSize);
    }


    private Connection makeAvailable(Connection connection) throws SQLException {
        if (isConnectionAvailable(connection)) {
            return connection;
        }
        occupiedPool.remove(connection);
        valueOfConnections--;
        connection.close();

        connection = createNewConnection();
        occupiedPool.add(connection);
        valueOfConnections++;
        return connection;
    }


    private Connection createNewConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(dataBaseUrl, userName, password);
        valueOfConnections++;
        occupiedPool.add(connection);
        return connection;
    }

    private boolean isConnectionAvailable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(SQL_VERIFY_CONN);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private Connection getConnectionFromPool() {
        Connection connection = null;
        if (freePool.size() > 0) {
            connection = freePool.pop();
            occupiedPool.add(connection);
        }
        return connection;
    }


}