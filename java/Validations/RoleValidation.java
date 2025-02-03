package Validations;

import java.util.Scanner;

public class RoleValidation
{
    Scanner sc = new Scanner(System.in);

    public String checkRoleValidation(String role)
    {
        String res = null;

        if(role.equals("admin") || role.equals("student"))
        {
            res = role;
        }
        else
        {
            while (true)
            {
                if(role.equals("admin") || role.equals("student"))
                {
                    res = role;
                    break;
                }
                else
                {
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("Invalid Role");
                    System.out.println("Please Insert Your Valid Role ( Admin / Student )");
                    System.out.println("---------------------------------------------------------------");

                    System.out.println("Enter Your Role :");
                    System.out.print("=>");
                    String temp = sc.next();
                    temp = new NameValidation().checkNameValidation(temp);
                    role = temp.toLowerCase();
                }
            }
        }
        return res;
    }
}
