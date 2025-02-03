package Main;

import Validations.IntInputValidation;

import java.util.Scanner;

public class Main
{
    public void initialMenu()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("---------------------------------------------------------------");
        System.out.println("|                   LIBRARY MANAGEMENT SYSTEM                 |");
        System.out.println("---------------------------------------------------------------");

        System.out.println("1 -> SignUp\n2 -> Login\n3 -> Exit\n");
        System.out.println("---------------------------------------------------------------");

        System.out.println("Enter Your choice: ");
        System.out.print("=> ");
        int ch = new IntInputValidation().checkInt();
        System.out.println("---------------------------------------------------------------");

        switch (ch)
        {
            case 1:
                new Signup().register();
                System.out.println("\n---------- Signup Done Successfully ----------\n");
                System.out.println("===================================================");

                System.out.println("Press 1 for Login or 0 for Exit");

                System.out.println("Enter Your choice: ");
                System.out.print("=> ");
                int next = new IntInputValidation().checkInt();

                if (next == 1)
                {
                    new Login().constructLogin();
                }
                else
                {
                    System.exit(1);
                }
                break;
            case 2:

                new Login().constructLogin();
                break;
            case 3:
                System.exit(1);
                break;
            default:
                System.out.println("\n---------------------------------------------------------------");

                System.out.println("Invalid Choice Please Enter Valid Choice.");
                System.out.println("---------------------------------------------------------------");
                System.out.println("Enter 0 to Exit or 1 to Continue");

                System.out.println("Enter Your choice: ");
                System.out.print("=> ");
                int exit = new IntInputValidation().checkInt();

                if(exit == 1)
                {
                    new Main().initialMenu();
                }
                else
                {
                    System.exit(1);
                }
        }
    }

    public static void main(String[] args)
    {

        new Main().initialMenu();
    }
}
