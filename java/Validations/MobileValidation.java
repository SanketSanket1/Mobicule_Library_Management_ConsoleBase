package Validations;

import java.util.Scanner;

public class MobileValidation
{
    Scanner sc = new Scanner(System.in);

    public String checkMobileValidation(String mobile)
    {
        while (true)
        {
            if(mobile.length() == 10 && mobile.matches("\\d{10}"))
            {
                return mobile;
            }
            else
            {
                System.out.println("Invalid Mobile Number. Please enter a valid 10-digit Mobile Number:");
                mobile = sc.next();
            }
        }
    }
}
