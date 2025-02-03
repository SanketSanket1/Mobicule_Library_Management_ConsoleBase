package Student_Side.User_Side_Controller;

import Main.Signup;
import Student_Side.User_Side_Service_Impl.DeleteAccount_Impl;
import Validations.IntInputValidation;
import Validations.NameValidation;
import Validations.PasswordValidation;

import java.util.Scanner;

public class DeleteAccount_Controller {

    Scanner sc = new Scanner(System.in);

    public void handleDeleteAccount(String role) {
        // Take user input
        System.out.println("Enter User Id: ");
        System.out.print("=> ");
        String uId = sc.next();
        uId = new NameValidation().checkNameValidation(uId);

        System.out.println("Enter Password: ");
        System.out.print("=> ");
        String pass = sc.next();
        pass = new PasswordValidation().checkPasswordValidation(pass);

        // Call the service layer with collected inputs
        boolean isDeleted = new DeleteAccount_Impl().deleteAccount(role, uId, pass);

        if (isDeleted) {
            System.out.println("Account Deleted Successfully.");
            askForSignup();
        } else {
            System.out.println("Invalid credentials or an issue occurred.");
            retryDelete(role);
        }
    }

    private void retryDelete(String role) {
        System.out.println("Press 0 to exit or 1 to retry:");
        System.out.print("=> ");
        int choice = new IntInputValidation().checkInt();

        if (choice == 1) {
            handleDeleteAccount(role);
        } else {
            System.exit(1);
        }
    }

    private void askForSignup() {
        System.out.println("Do you want to sign up for a new account? (Enter 1 for YES, 0 for EXIT)");
        System.out.print("=> ");
        int choice = new IntInputValidation().checkInt();

        if (choice == 1) {
            new Signup().register();
        } else {
            System.exit(1);
        }
    }
}
