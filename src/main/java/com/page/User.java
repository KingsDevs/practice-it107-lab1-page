package com.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User 
{
    public static final String visitor = "VISITOR"; 
    public static final String editor = "EDITOR"; 
    public static final String admin = "ADMIN"; 

    public static void insertUser(String username, String password, String role, Connect connect) throws ClassNotFoundException, SQLException
    {
        String sql = "INSERT INTO users(username, password, role) ";
        sql += "VALUES(?,?,?)";

        Connection connection = connect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, role);

        preparedStatement.executeUpdate();
    }
    
    public static Boolean userExist(String username, String password, Connect connect) throws ClassNotFoundException, SQLException
    {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        Connection connection = connect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        String dbusername = "";
        String dbpassword = "";

        while (resultSet.next()) 
        {
            dbusername = resultSet.getString("username");
            dbpassword = resultSet.getString("password");
        }

        if(dbusername.equals(username) && dbpassword.equals(password))
            return true;

        return false;
    }
}
