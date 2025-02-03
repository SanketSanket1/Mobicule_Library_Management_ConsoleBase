package Main;

import Dao.Dao_Impl.Authentication_Dao_Impl;
import Validations.*;

import java.sql.*;
import java.util.Scanner;

public class Login
{

    public void constructLogin()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Your Role :");
        System.out.println("1 -> Admin\n2 -> Student");
        System.out.print("=>");
        int ch = new IntInputValidation().checkInt();

        String role = "";

        switch (ch)
        {
            case 1:
                role = "admin";
                break;
            case 2:
                role = "student";
            default:
                System.out.println("Invalid Choice");
        }

        userLogin(role);
    }

    public void userLogin(String role)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter User Id : ");
        System.out.print("=>");
        String uId = sc.next();
        uId = new NameValidation().checkNameValidation(uId);

        System.out.println("Enter Password : ");
        System.out.print("=>");
        String pass = sc.next();
        pass = new PasswordValidation().checkPasswordValidation(pass);

        try
        {
            if (new Authentication_Dao_Impl().validateLogin(role, uId, pass))
            {
                System.out.println("          _________________________________");
                System.out.println("               Login Successfully Done     ");
                System.out.println("          _________________________________");
                new HomePage().menu(role,uId);
            }
            else
            {
                System.out.println("---------------------------------------------------------------");
                System.out.println("Invalid User Name and Password");
                System.out.println("press 0 to exit or 1 to continue :");
                System.out.print("=>");
                int exit = new IntInputValidation().checkInt();
                if (exit == 0)
                {
                    System.exit(1);
                }
                else
                {
                    System.out.println("\nEnter User Id and Password Again\n");
                    userLogin(role);
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error while validating login", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
