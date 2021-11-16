package com.example.javafxco1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://10.14.0.12:3306/ipenversion2db","elcademy","elcademy1234");
        return connection;
    }
}
