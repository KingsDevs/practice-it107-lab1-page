package com.page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App 
{
    private static Scanner scanner = new Scanner(System.in);

    private static void login(String role, Connect connect)
    {
        if(!role.equals(User.visitor))
        {
            if(role.equals(User.admin))
                connect = new Connect(role, "11111111");
            else
                connect = new Connect(role, "22222222");
        }

        while(true)
        {

            System.out.println("1. Add Post");
            System.out.println("2. Edit Post");
            System.out.println("3. Delete Post");
            System.out.println("4. View All Post");
            System.out.println("5. Logout");

            System.out.print(">> ");
            int input = scanner.nextInt();
            

            if(input == 1)
            {
                System.out.print("Enter Post: ");
                scanner.nextLine();
                String content = scanner.nextLine();


                try {
                    Post.addPost(content, connect);
                    System.out.println("Post Added!");
                } catch (ClassNotFoundException | SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
            else if(input == 2)
            {
                System.out.println("edit post ....");
            }
            else if(input == 3)
            {
                System.out.println("delete post ....");
            }
            else if(input == 4)
            {
                try {
                    ResultSet resultSet = Post.selectPost(connect);
                    int count = 1;

                    while (resultSet.next()) 
                    {
                        Post post = new Post(resultSet.getInt("post_id"), resultSet.getString("content"));
                        System.out.println(count + " " + post.toString());
                        count++;
                    }

                } catch (ClassNotFoundException | SQLException e) {
                    System.err.println(e.getMessage());
                }
                
            }
            else
            {
                System.out.println("Logout!");
                break;
            }

            scanner.nextLine();
        }
    }
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
        Connect defaultConnect = new Connect("visitor", "33333333");
        

        while (true) 
        {
            System.out.println("1. Log In");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            
            System.out.print(">> ");
            int input = scanner.nextInt();

            if(input == 1)
            {
                while (true) 
                {
                    System.out.print("Enter Username: ");
                    String username = scanner.next();
    
                    System.out.print("Enter Password: ");
                    String password = scanner.next();

                    User user = User.getUser(username, password, defaultConnect);

                    if(user != null)
                    {
                        login(user.getRole() , defaultConnect);
                        break;
                    }
                    else
                        System.out.println("Wrong username or password!");
                    
                }
                
            }
            else if(input == 2)
            {
                System.out.print("Enter Username: ");
                String username = scanner.next();

                System.out.print("Enter Password: ");
                String password = scanner.next();

                String role;

                while (true) 
                {
                    System.out.println("Choose a Role!");
                    System.out.println("1. Admin");   
                    System.out.println("2. Editor");   
                    System.out.println("3. Visitor");
                    
                    System.out.print(">> ");
                    input = scanner.nextInt();

                    if(input == 1)
                    {
                        role = User.admin;
                        break;
                    }
                    else if(input == 2)
                    {
                        role = User.editor;
                        break;
                    }
                    else if(input == 3)
                    {
                        role = User.visitor;
                        break;
                    }
                    else
                        System.out.println("Choose a Valid Role!");
                }

                try {
                    User.insertUser(username, password, role, defaultConnect);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                break;
            }
        }

        scanner.close();
    }
}
