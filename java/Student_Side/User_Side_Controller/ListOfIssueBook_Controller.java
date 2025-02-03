package Student_Side.User_Side_Controller;

import Student_Side.User_Side_Service.ListOfIssueBook;
import Student_Side.User_Side_Service_Impl.ListOfIssueBook_Impl;
import Main.HomePage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class ListOfIssueBook_Controller
{
    public void handleListIssueBook(String role, String uId) {
        try {
            ResultSet rs = new ListOfIssueBook_Impl().listIssueBook(uId);
            if (rs != null && rs.next()) {
                // Display book issue details
                System.out.printf("%-15s %-15s %-15s\n", "Book Name", "Due Date", "Status");
                System.out.println("-----------------------------------------");

                do {
                    String bName = rs.getString(1);
                    Date dueDate = rs.getDate(2);
                    String status = rs.getString(3);

                    System.out.printf("%-15s %-15s %-15s\n", bName, dueDate, status);
                } while (rs.next());

            } else {
                System.out.println("---------------------------------------------------------------");
                System.out.println("No book issued for this User ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving issued books: " + e.getMessage());
        }
        new HomePage().menu(role, uId);
    }
}

