package Admin_Side.Admin_Side_Service_Impl;

import Admin_Side.Admin_Side_Service.IssueBook_Service;
import Dao.Dao_Impl.Admin_Dao_Impl;
import Main.HomePage;
import Validations.NameValidation;

import java.sql.ResultSet;
import java.time.LocalDate;

public class IssueBook_Service_Impl extends Admin_Dao_Impl implements IssueBook_Service
{

    public void issueNewBook(String role, String uId, String userId, int isbn) {
        try {
            // Validate userId using NameValidation
            userId = new NameValidation().checkNameValidation(userId);

            // Find due date (6 months from issue date)
            LocalDate returnDate = new Admin_Dao_Impl().dueDate();

            // Check count of books for the particular userId
            ResultSet rs = issueCountDao(userId);
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }

            if (count <= 5) {
                // Call the DAO method to issue the book
                boolean isIssued = issueBookDao(userId, isbn, returnDate);

                if (isIssued) {
                    System.out.println("Book Issued Successfully");
                } else {
                    System.out.println("Failed to issue book.");
                }
            } else {
                System.out.println("Already Five Books Issued on This UserId (only 5 Books can be issued per UserId)");
            }

            // Return to the home page menu
            new HomePage().menu(role, uId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}



