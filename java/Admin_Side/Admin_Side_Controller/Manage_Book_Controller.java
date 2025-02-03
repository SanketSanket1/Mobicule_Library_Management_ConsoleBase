package Admin_Side.Admin_Side_Controller;

import Admin_Side.Admin_Side_Service_Impl.Manage_Book_Impl;
import Validations.IntInputValidation;

import java.util.Scanner;

public class Manage_Book_Controller
{
    public void handleBookOperations(String role, String uId) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose an operation: ");
        System.out.println("1 -> Ascending Sort Book List");
        System.out.println("2 -> Descending Sort Book List");
        System.out.println("3 -> Count Books");
        System.out.println("4 -> Remove Book");
        System.out.println("5 -> Update Book");
        System.out.println("6 -> Insert Book");
        System.out.println("7 -> Show Categories");

        System.out.print("Enter your choice: ");
        int choice = new IntInputValidation().checkInt();

        switch (choice) {
            case 1:
                new Manage_Book_Impl().ascSortBookList(role, uId);
                break;
            case 2:
                new Manage_Book_Impl().descSortBookList(role, uId);
                break;
            case 3:
                new Manage_Book_Impl().countBook(role, uId);
                break;
            case 4:
                new Manage_Book_Impl().removeBookInfo(role, uId);
                break;
            case 5:
                new Manage_Book_Impl().updateBookInfo(role, uId);
                break;
            case 6:
                new Manage_Book_Impl().insertBookInfo(role, uId);
                break;
            case 7:
                new Manage_Book_Impl().categoryBookInfo(role, uId);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                handleBookOperations(role, uId); // Recurse if invalid
                break;
        }
    }
}

