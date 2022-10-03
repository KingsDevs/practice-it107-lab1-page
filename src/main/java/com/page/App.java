package com.page;

import java.sql.SQLException;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
        Connect defaultConnect = new Connect("visitor", "33333333");
        Scanner scanner = new Scanner(System.in);

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

                    if(User.userExist(username, password, defaultConnect))
                    {
                        System.out.println("Logged In!");
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
