package Validations;

import java.util.Scanner;

public class NameValidation
{
    Scanner sc = new Scanner(System.in);
    public String checkNameValidation(String name)
    {
        if(name.charAt(0) < (char)65 || (name.charAt(0) > (char)90 && name.charAt(0) < (char)97) || name.charAt(0) > (char)122)
        {
            System.out.println("Invalid Name!!! Name must Starts with Character(Alpha-Numeric)\n");
            System.out.println("Enter Valid user Name : ");
            System.out.println("=> ");
            name = sc.next();

            while (true) {
                if(name.charAt(0) < (char)65 || (name.charAt(0) > (char)90 && name.charAt(0) < (char)97) || name.charAt(0) > (char)122) {
                    System.out.println("Enter User Name (Alpha-Numeric) : ");
                    System.out.println("=> ");
                    name = sc.next();
                }
                else
                {
                    break;
                }
            }
        }
        return name;
    }
}