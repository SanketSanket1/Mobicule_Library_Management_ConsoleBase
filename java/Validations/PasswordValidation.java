package Validations;

import java.util.Scanner;
import java.util.regex.Pattern;

public class PasswordValidation
{
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    Scanner sc = new Scanner(System.in);

    public String checkPasswordValidation(String password)
    {
        if (!Pattern.matches(PASSWORD_REGEX, password))
        {
            System.out.println("Password is Weak Create Password using following Parameters :\n" +
                    "1) At least one lowercase letter (a-z).\n" +
                    "2) At least one uppercase letter (A-Z).\n" +
                    "3) At least one digit (0-9).\n" +
                    "4) At least one special character from the set @, $, !, %, *, ?, or &.\n" +
                    "5) At least 8 characters long.\n" +
                    "6) The string must consist of only alphanumeric characters and specific special characters (@, $, !, %, *, ?, &).");

            System.out.println("Enter Strong Password : ");
            System.out.println("=> ");
            password = sc.next();

            while (true)
            {
                if (!Pattern.matches(PASSWORD_REGEX, password))
                {
                    System.out.println("Password is Weak Create Password using following Parameters :\n" +
                            "1) At least one lowercase letter (a-z).\n" +
                            "2) At least one uppercase letter (A-Z).\n" +
                            "3) At least one digit (0-9).\n" +
                            "4) At least one special character from the set @, $, !, %, *, ?, or &.\n" +
                            "5) At least 8 characters long.\n" +
                            "6) The string must consist of only alphanumeric characters and specific special characters (@, $, !, %, *, ?, &).");

                    System.out.println("Enter Strong Password : ");
                    System.out.println("=> ");
                    password = sc.next();

                }
                else
                {
                    break;
                }
            }
        }
        return password;
    }
}
