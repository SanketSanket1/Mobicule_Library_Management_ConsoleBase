package Validations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateValidation
{
    Scanner sc = new Scanner(System.in);
    public LocalDate checkDateValidation(String rdate)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true)
        {
            // Loop until valid date is entered
            try {
                return LocalDate.parse(rdate, formatter);  // Parse and return if valid
            }
            catch (DateTimeParseException e)
            {
                System.out.println("Invalid Date Format! Please enter a date in (YYYY-MM-DD): ");
                System.out.println("\nEnter Valid Date in Format(YYYY-MM-DD) : ");
                System.out.print("=> ");
                rdate = sc.next();  // Ask user for new input
            }
        }
    }
}
