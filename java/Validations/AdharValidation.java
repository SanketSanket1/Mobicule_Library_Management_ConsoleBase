package Validations;

import java.util.Scanner;

public class AdharValidation
{
    Scanner sc = new Scanner(System.in);

    public String checkAdharValidation(String aadhar)
    {
        while (true)
        {
            if (aadhar.length() == 12 && aadhar.matches("\\d{12}"))
            {
                return aadhar;
            }
            else
            {
                System.out.println("Invalid Aadhaar Number. Please enter a valid 12-digit Aadhaar Number:");
                aadhar = sc.next();
            }
        }
    }
}
