package Main;

import Admin_Side.Admin_Side_Controller.Additional_Info_Controller;
import Dao.Dao_Impl.Authentication_Dao_Impl;
import Validations.*;

import java.util.Scanner;

public class Signup
{

    public void register()
    {
        Scanner sc = new Scanner(System.in);
        int flag = 0;

        try
        {
            // Input validation
            System.out.println("Enter User Id : ");
            String uId = sc.next();
            uId = new NameValidation().checkNameValidation(uId);

            System.out.println("Enter Password : ");
            System.out.println("=> ");
            String pass = sc.next();
            pass = new PasswordValidation().checkPasswordValidation(pass);


            System.out.println("ReEnter Same Password : ");
            String cPass = sc.next();

            if (!pass.equals(cPass))
            {
                System.out.println("Please Enter same Password in both Field");
                System.out.println("ReEnter Password Again :");
                cPass = sc.next();

                while (true)
                {
                    if (!pass.equals(cPass))
                    {
                        System.out.println("Please Enter same Password in both Field");
                        System.out.println("ReEnter Password Again :");
                        cPass = sc.next();
                    }
                    else
                    {
                        break;
                    }
                }
            }

            // Check if the user already exists
            if (new Authentication_Dao_Impl().isUserExists(uId))
            {
                System.out.println("This User is Already Exist");
                System.out.println("Please Signup with a different User Id");
                System.out.println("press 0 to exit or 1 to Continue");

                System.out.println("Enter Your choice: ");
                System.out.print("=> ");
                int exit = new IntInputValidation().checkInt();
                if (exit == 0)
                {
                    System.exit(1);
                }
                else if (exit == 1)
                {
                    System.out.println("\nEnter All Details Again\n");
                    register();
                }
                else
                {
                    System.exit(1);
                }
            }
            else
            {
                // Proceed with the signup
                System.out.println("Please Wait !!!");

                if (new Check_Authentication().isPermitted(uId, pass, cPass))
                {
                    pass = EcryptionDecryption.encryptPassword(pass);
                    cPass = EcryptionDecryption.encryptPassword(cPass);

                    if (new Authentication_Dao_Impl().SignUpUser(uId, pass, cPass))
                    {
                        System.out.println("UserId and Password set Successfully.\n");
                        new Additional_Info_Controller().handleAdditionalInfo(uId);  // Insert additional information
                    }
                    else
                    {
                        System.out.println("Some Problem Occurred.");
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error during registration", e);
        }
    }
}

