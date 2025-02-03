package Admin_Side.Admin_Side_Service_Impl;

import Admin_Side.Admin_Side_Service.Manage_Student;
import Dao.Dao_Impl.User_Dao_Impl;
import Main.Signup;
import Dao.Dao_Impl.Admin_Dao_Impl;
import Main.HomePage;
import Validations.*;

import java.sql.*;
import java.util.Scanner;

public class Manage_Student_Impl implements Manage_Student
{

    public void sortStudentListAsc(String role,String uId)
    {
        try
        {
            ResultSet rs = new Admin_Dao_Impl().getAllStudentsAsc();
            printStudentList(rs);
            new HomePage().menu(role,uId);
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error while fetching student list", e);
        }
    }

    public void sortStudentListDesc(String role,String uId)
    {
        try
        {
            ResultSet rs = new Admin_Dao_Impl().getAllStudentsDesc();
            printStudentList(rs);
            new HomePage().menu(role,uId);
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error while fetching student list", e);
        }
    }

    public void countStudents(String role,String uId)
    {
        try
        {
            int count = new Admin_Dao_Impl().getStudentCount();
            System.out.println("Total Count of Students: " + count);
            new HomePage().menu(role,uId);
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error while counting students", e);
        }
    }

    public void removeStudent(String role,String uId)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter User Id: ");
        String userId = sc.next();
        userId = new NameValidation().checkNameValidation(userId);

        try
        {
            ResultSet resultSet = new User_Dao_Impl().getStudentRecord(userId);
            if(resultSet.next()) 
            {
                String fName = resultSet.getString(2);
                String lName = resultSet.getString(3);
                String email = resultSet.getString(4);
                String mobile = resultSet.getString(5);
                String adhar = resultSet.getString(6);

                int isInsert = new User_Dao_Impl().insertDeletedRecord(fName, lName, email, mobile, adhar, uId);

                int rs = 0, result1 = 0, result2 = 0;
                if (isInsert == 1)
                {
                    result1 = new Admin_Dao_Impl().deleteStudentByUserId(userId);
                    result2 = new Admin_Dao_Impl().deleteStudentFromStudentTable(userId);
                }

                if (result1 > 0 && result2 > 0)
                {
                    System.out.println("Record Deleted Successfully.");
                }
                else 
                {
                    System.out.println("Record Not Deleted");
                }
                new HomePage().menu(role, uId);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error while deleting student", e);
        }
    }

    public void updateStudentInfo(String role,String uId)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter user Id: ");
        String userId = sc.next();
        userId = new NameValidation().checkNameValidation(userId);

        System.out.println("What do you want to update?");
        System.out.println("1 -> First Name\n2 -> Last Name\n3 -> Email\n4 -> Mobile\n5 -> Adhar Number");

        System.out.println("Enter Your choice: ");
        System.out.print("=> ");
        int choice = new IntInputValidation().checkInt();

        String field = "";
        String value = "";

        try
        {
            switch (choice)
            {
                case 1:
                    System.out.println("Enter First Name: ");
                    value = new NameValidation().checkNameValidation(sc.next());
                    field = "fname";
                    break;
                case 2:
                    System.out.println("Enter Last Name: ");
                    value = new NameValidation().checkNameValidation(sc.next());
                    field = "lname";
                    break;
                case 3:
                    System.out.println("Enter Email: ");
                    value = new EmailValidation().checkEmailValidation(sc.next());
                    field = "email";
                    break;
                case 4:
                    System.out.println("Enter Mobile: ");
                    value = new MobileValidation().checkMobileValidation(sc.next());
                    field = "mobile";
                    break;
                case 5:
                    System.out.println("Enter Adhar: ");
                    value = new AdharValidation().checkAdharValidation(sc.next());
                    field = "adhar";
                    break;
                default:
                    System.out.println("Invalid Choice");
                    new HomePage().menu(role,uId);
                    return;
            }

            int result = new Admin_Dao_Impl().updateStudentInfo(userId, field, value);
            if (result > 0)
            {
                System.out.println("Record Updated Successfully");
            }
            else
            {
                System.out.println("Record Not Updated");
            }
            new HomePage().menu(role,uId);
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error while updating student info", e);
        }
    }

    public void insertStudentInfo(String role,String uId)
    {
        new Signup().register();
        new HomePage().menu(role,uId);
    }

    public void printStudentList(ResultSet rs) throws SQLException
    {
        System.out.printf("%-10s %-15s %-15s %-25s %-20s %-15s\n", "id", "First Name", "Last Name", "Email", "Mobile", "Adhar Card");
        while (rs.next())
        {
            int id = rs.getInt(1);
            String fname = rs.getString(2);
            String lname = rs.getString(3);
            String email = rs.getString(4);
            String mobile = rs.getString(5);
            String adhar = rs.getString(6);
            System.out.printf("%-10s %-15s %-15s %-25s %-20s %-15s\n", id, fname, lname, email, mobile, adhar);
        }
    }
}

