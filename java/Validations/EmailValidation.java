package Validations;

import java.util.Scanner;
import java.util.regex.Pattern;

public class EmailValidation
{
    static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$";

    public String checkEmailValidation(String email)
    {
        Scanner sc = new Scanner(System.in);

        if(!Pattern.matches(EMAIL_REGEX,email))
        {
            System.out.println("Incorrect Email Id .\n");
            System.out.println("Enter Valid Email Id : ");
            email = sc.next();

            while (true)
            {
                if(!Pattern.matches(EMAIL_REGEX,email))
                {
                    System.out.println("Incorrect Email Id .\n");
                    System.out.println("Enter Valid Email Id : ");
                    email = sc.next();
                }
                else
                {
                    break;
                }
            }
        }
        return email;
    }
}
