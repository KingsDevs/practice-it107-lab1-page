package com.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Post 
{
    private int postId;
    private String content;

    public Post(int postId, String content)
    {
        this.postId = postId;
        this.content = content;
    }

    public Integer getPostId()
    {
        return postId;
    }

    public String getContent()
    {
        return content;
    }

    @Override
    public String toString()
    {
        return content;
    }

    public static void addPost(String content, Connect connect) throws ClassNotFoundException, SQLException
    {
        String sql = "INSERT INTO page.post VALUES(DEFAULT, ?)";

        Connection connection = connect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, content);
        preparedStatement.executeUpdate();
    }

    public static ResultSet selectPost(Connect connect) throws ClassNotFoundException, SQLException
    {
        String sql = "SELECT * FROM page.post";

        Connection connection = connect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        return rs;
    }
}
