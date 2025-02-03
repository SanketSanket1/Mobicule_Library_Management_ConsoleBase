package Main;

import Admin_Side.Admin_Side_Controller.*;
import Student_Side.User_Side_Controller.DeleteAccount_Controller;
import Student_Side.User_Side_Controller.ForgotPassword_Controller;
import Student_Side.User_Side_Controller.ListOfIssueBook_Controller;
import Student_Side.User_Side_Controller.ViewAllDue_Controller;
import Validations.IntInputValidation;

import java.util.Scanner;

public class HomePage extends Login
{
    public void menu(String role, String uId)
    {
        System.out.println("\n\n\n");
        System.out.println("---------------------------------------------------------------");
        System.out.println("|                                                             |");
        System.out.println("|      ##########     WELCOME TO HOME PAGE     ##########     |");
        System.out.println("|                                                             |");
        System.out.println("---------------------------------------------------------------\n\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~ADMIN MAIN MENU~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");

        Scanner sc = new Scanner(System.in);

        if(role.equals("admin"))
        {
            System.out.println("Which operation of the following you want to perform :");
            System.out.println("---------------------------------------------------------------");
            System.out.println("1 -> Manage Student\n2 -> Manage Book\n3 -> View Student List\n4 -> View Book List\n5 -> View Defaulter\n6 -> Issue Book\n7 -> Return Book\n8 -> Exit\n");
            System.out.println("---------------------------------------------------------------");

            System.out.println("Enter Your choice: ");
            System.out.print("=> ");
            int ch = new IntInputValidation().checkInt();
            System.out.println("---------------------------------------------------------------");

            switch (ch)
            {
                case 1:
                    new Manage_Student_Controller().handleStudentOperations(role,uId);
                    break;
                case 2:
                    new Manage_Book_Controller().handleBookOperations(role,uId);
                    break;
                case 3:
                    new ViewStudentList_Controller().handleViewStudentList(role,uId);
                    break;
                case 4:
                    new ViewBookList_Controller().handleViewBooks(role,uId);
                    break;
                case 5:
                    new ViewDefaulter_Controller().handleViewDefaulterList(role,uId);
                    break;
                case 6:
                    new IssueBook_Controller().handleIssueBook(role,uId);
                    break;
                case 7:
                    new ReturnBook_Controller().handleReturnBook(role,uId);
                    break;
                case 8:
                    System.exit(1);
                    break;
                default:
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("                               Invalid Choice");
                    System.out.println("---------------------------------------------------------------");
                    menu(role,uId);
            }
        }
        else
        {
            System.out.println("Which operation of the following you want to perform :");
            System.out.println("---------------------------------------------------------------");
            System.out.println("1 -> View Due Date \n2 -> List of Issued Book\n3 -> Forgot Password\n4 -> Delete Account\n5 -> Exit\n");
            System.out.println("---------------------------------------------------------------");

            System.out.println("Enter Your choice: ");
            System.out.print("=> ");
            int ch = new IntInputValidation().checkInt();
            System.out.println("---------------------------------------------------------------");

            switch (ch)
            {
                case 1:
                    new ViewAllDue_Controller().handleViewDue(role,uId);
                    break;
                case 2:
                    new ListOfIssueBook_Controller().handleListIssueBook(role,uId);
                    break;
                case 3:
                    new ForgotPassword_Controller().handleForgotPassword(role,uId);
                    break;
                case 4:
                    new DeleteAccount_Controller().handleDeleteAccount(role);
                case 5:
                    System.exit(1);
                    break;
                default:
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("                               Invalid Choice");
                    System.out.println("---------------------------------------------------------------");
                    menu(role,uId);
            }
        }
    }
}