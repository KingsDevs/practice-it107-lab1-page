package com.page;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect 
{
    
    private String dbUrl = "/localhost:5432/minipage";
    private Connection connection = null;
    private Statement statement = null;

    private String username;
    private String password;

    public Connect(String username, String password)
    {
        this.username = username;
        this.password = password;

        try {
            getConnection();
            getStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException
    {
        if(connection == null)
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql:/" + dbUrl, username, password);
        }

        return connection;
    }

    public Statement getStatement() throws ClassNotFoundException, SQLException
    {
        if(statement == null)
        {
            statement = getConnection().createStatement();
        }

        return statement;
    }

}
