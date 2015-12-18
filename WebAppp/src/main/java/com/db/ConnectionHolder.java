package com.db;

import java.sql.Connection;

/**
 * ConnectionHolder class- it's a holder for  a thread safe connection
 */
public class ConnectionHolder {

    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    /**
     * SetConnection method- set a connection
     *
     * @param connection
     */
    public static void setConnection(Connection connection) {
        connectionHolder.set(connection);
    }

    /**
     * GetConnection method- get a connection
     *
     * @return Connection
     */
    public static Connection getConnection() {
        return connectionHolder.get();
    }

}
