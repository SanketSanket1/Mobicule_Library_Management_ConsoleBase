package Validations;

import java.util.Scanner;

public class IntInputValidation
{
    public int checkInt()
    {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true)
        {
            if (sc.hasNextInt())
            {
                choice = sc.nextInt();
                break;
            }
            else
            {
                System.out.println("Invalid input. Please enter a valid integer : ");
                sc.next();
            }
        }
        return choice;
    }
}
