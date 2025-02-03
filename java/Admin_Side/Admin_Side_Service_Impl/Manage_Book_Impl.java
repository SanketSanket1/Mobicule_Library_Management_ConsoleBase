package Admin_Side.Admin_Side_Service_Impl;

import Admin_Side.Admin_Side_Service.Manage_Book;
import Dao.Dao_Impl.Admin_Dao_Impl;
import Main.HomePage;
import Validations.IntInputValidation;

import java.sql.*;
import java.util.Scanner;

public class Manage_Book_Impl implements Manage_Book {

    public void ascSortBookList(String role, String uId) {
        ResultSet rs = new Admin_Dao_Impl().getBooksSortedByName("ASC");

        System.out.printf("%-10s %-15s %-15s %-25s %-20s %-15s\n", "id", "ISBN Number", "Book Name", "Author Name", "No Of Pages", "Category");

        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                int isbn = rs.getInt(2);
                String bname = rs.getString(3);
                String Author = rs.getString(4);
                int no_of_page = rs.getInt(5);
                String Category = rs.getString(6);

                System.out.printf("%-10s %-15s %-15s %-25s %-20s %-15s\n", id, isbn, bname, Author, no_of_page, Category);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        new HomePage().menu(role, uId);
    }

    public void descSortBookList(String role, String uId)
    {
        ResultSet rs = new Admin_Dao_Impl().getBooksSortedByName("DESC");

        System.out.printf("%-10s %-15s %-15s %-25s %-20s %-15s\n", "id", "ISBN Number", "Book Name", "Author Name", "No Of Pages", "Category");

        try
        {
            while (rs.next())
            {
                int id = rs.getInt(1);
                int isbn = rs.getInt(2);
                String bname = rs.getString(3);
                String Author = rs.getString(4);
                int no_of_page = rs.getInt(5);
                String Category = rs.getString(6);

                System.out.printf("%-10s %-15s %-15s %-25s %-20s %-15s\n", id, isbn, bname, Author, no_of_page, Category);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        new HomePage().menu(role, uId);
    }

    public void countBook(String role, String uId)
    {
        int count = new Admin_Dao_Impl().getBookCount();
        System.out.println("Total Count of Books: " + count);
        new HomePage().menu(role, uId);
    }

    public void removeBookInfo(String role, String uId)
    {
        System.out.println("Enter ISBN Id: ");
        int isbn = new IntInputValidation().checkInt();

        boolean isDeleted = new Admin_Dao_Impl().deleteBookByISBN(isbn);
        if (isDeleted)
        {
            System.out.println("Record Deleted Successfully.");
        }
        else
        {
            System.out.println("Record not Found.");
        }

        new HomePage().menu(role, uId);
    }

    public void updateBookInfo(String role, String uId)
    {
        if (!role.equalsIgnoreCase("admin"))
        {
            System.out.println("Unauthorized access! Only admins can update books.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        IntInputValidation intValidator = new IntInputValidation();

        System.out.println("Enter Book ISBN to update: ");
        int isbn = intValidator.checkInt(); // Use the validation method for integers

        System.out.println("1 -> Book Name\n2 -> Author\n3 -> No of Pages\n4 -> Category\n5 -> Quantity\n6 -> Goto Main Menu\n");

        System.out.println("Enter Your Choice : ");
        int ch = new IntInputValidation().checkInt();
        String column = "";

        switch (ch)
        {
            case 1:
                column = "bname";
                break;
            case 2:
                column = "author";
                break;
            case 3:
                column = "no_of_page";
                break;
            case 4:
                column = "btype";
                break;
            case 5:
                column = "book_quantity";
                break;
            case 6:
                new HomePage().menu(role,uId);
                break;
            default:
                System.out.println("Choice Is Invalid");
        }

        System.out.println("Enter the new value for " + column + ": ");
        String newValue = sc.nextLine().trim(); // New value for the chosen column

        boolean isUpdated = new Admin_Dao_Impl().updateBookInfo(isbn, column, newValue); // Calling the DAO method

        if (isUpdated)
        {
            System.out.println("Book information updated successfully!");
            new HomePage().menu(role,uId);
        }
        else
        {
            System.out.println("Failed to update book. Please check the input and try again.");
            new HomePage().menu(role,uId);
        }
    }



    public void insertBookInfo(String role, String uId)
    {
        if (!role.equalsIgnoreCase("admin"))
        {
            System.out.println("Unauthorized access! Only admins can insert books.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Book ISBN: ");
        int isbn = new IntInputValidation().checkInt();

        System.out.println("Enter Book Name: ");
        String bookName = sc.nextLine();

        System.out.println("Enter Author Name: ");
        String authorName = sc.nextLine();

        System.out.println("Enter Number of Pages: ");
        int numOfPages = new IntInputValidation().checkInt();

        System.out.println("Enter Book Category: ");
        String category = sc.nextLine();

        System.out.println("Enter Quantity: ");
        int quantity = new IntInputValidation().checkInt();

        boolean isInserted = new Admin_Dao_Impl().insertBook(isbn, bookName, authorName, numOfPages, category, quantity);

        if (isInserted)
        {
            System.out.println("Book inserted successfully!");
            new HomePage().menu(role,uId);
        }
        else
        {
            System.out.println("Failed to insert book. Please try again.");
        }

    }

    public void categoryBookInfo(String role, String uId) {
        ResultSet rs = new Admin_Dao_Impl().getDistinctCategories();
        System.out.println("Distinct Categories: ");
        try {
            while (rs.next()) {
                String category = rs.getString(1);
                System.out.println(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new HomePage().menu(role, uId);
    }
}
