package Admin_Side.Admin_Side_Controller;

import Admin_Side.Admin_Side_Service_Impl.Additional_Info_Impl;
import Validations.AdharValidation;
import Validations.EmailValidation;
import Validations.MobileValidation;
import Validations.NameValidation;

import java.util.Scanner;

public class Additional_Info_Controller
{

    public void handleAdditionalInfo(String uId)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("********** Insert Additional Information **********");

        System.out.println("Enter Your First Name: ");
        String fName = sc.next();
        fName = new NameValidation().checkNameValidation(fName);

        System.out.println("Enter Your Last Name: ");
        String lName = sc.next();
        lName = new NameValidation().checkNameValidation(lName);

        System.out.println("Enter Email Id: ");
        String email = sc.next();
        email = new EmailValidation().checkEmailValidation(email);

        System.out.println("Enter Mobile Number: ");
        String mobile = sc.next();
        mobile = new MobileValidation().checkMobileValidation(mobile);

        System.out.println("Enter Aadhaar Card: ");
        String adhar = sc.next();
        adhar = new AdharValidation().checkAdharValidation(adhar);

        boolean isInserted = new Additional_Info_Impl().insertAdditionalInfo(fName, lName, email, mobile, adhar, uId);
        if (isInserted) {
            System.out.println("Record Inserted Successfully\n");
        } else {
            System.out.println("Record Not Inserted");
        }
    }
}

