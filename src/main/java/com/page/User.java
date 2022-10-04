package com.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User 
{
    public static final String visitor = "visitor"; 
    public static final String editor = "editor"; 
    public static final String admin = "admin"; 

    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role)
    {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getRole()
    {
        return role;
    }

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

    public static User getUser(String username, String password, Connect connect) throws ClassNotFoundException, SQLException
    {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        Connection connection = connect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next())
        {
            user = new User(resultSet.getString("username"), 
                            resultSet.getString("password"), 
                            resultSet.getString("role"));
        }

        return user;
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
