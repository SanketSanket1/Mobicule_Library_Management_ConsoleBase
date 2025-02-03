package Student_Side.User_Side_Controller;

import Student_Side.User_Side_Service.ViewAllDue;
import Student_Side.User_Side_Service_Impl.ViewAllDue_Impl;
import Main.HomePage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class ViewAllDue_Controller
{
    public void handleViewDue(String role, String uId) {
        try {
            ResultSet rs = new ViewAllDue_Impl().viewDue(uId);
            if (rs != null && rs.next()) {
                // Display due book details
                System.out.printf("%-15s %-15s\n", "Book Name", "Due Date");
                System.out.println("-----------------------------------------");

                do {
                    String bName = rs.getString(1);
                    Date dueDate = rs.getDate(2);
                    System.out.printf("%-20s %-15s\n", bName, dueDate);
                } while (rs.next());

            } else {
                System.out.println("---------------------------------------------------------------");
                System.out.println("No book dues for this User ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving due books: " + e.getMessage());
        }
        new HomePage().menu(role, uId);
    }
}

