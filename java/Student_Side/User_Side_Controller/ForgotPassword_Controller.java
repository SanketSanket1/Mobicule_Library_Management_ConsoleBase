package Student_Side.User_Side_Controller;

import Student_Side.User_Side_Service.ForgotPassword;
import Student_Side.User_Side_Service_Impl.ForgotPassword_Impl;
import Validations.AdharValidation;
import Validations.NameValidation;
import Validations.PasswordValidation;

import java.util.Scanner;

public class ForgotPassword_Controller
{
    Scanner sc = new Scanner(System.in);

    public void handleForgotPassword(String role, String userId)
    {
        System.out.println("Enter User Id : ");
        System.out.print("=> ");
        String uId = sc.next();
        uId = new NameValidation().checkNameValidation(uId);

        System.out.println("Enter Adhar Card : ");
        System.out.print("=> ");
        String adhar = sc.next();
        adhar = new AdharValidation().checkAdharValidation(adhar);

        System.out.println("Enter New Password : ");
        System.out.print("=> ");
        String newPassword = sc.next();
        newPassword = new PasswordValidation().checkPasswordValidation(newPassword);

        // Now pass all collected data to the Service Layer
        new ForgotPassword_Impl().forgot(role, userId, uId, adhar, newPassword);
    }
}
