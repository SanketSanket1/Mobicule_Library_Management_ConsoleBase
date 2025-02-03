package Admin_Side.Admin_Side_Service_Impl;

import Admin_Side.Admin_Side_Service.ReturnBook;
import Dao.Dao_Impl.Admin_Dao_Impl;
import Main.HomePage;

public class ReturnBook_Impl implements ReturnBook
{

    @Override
    public void returnBook(String uName, String bName, String role, String uId) {
        // Step 1: Get issueId from DAO
        int issueId = new Admin_Dao_Impl().getIssueIdByUserName(uName, bName);
        if (issueId == -1) {
            System.out.println("No Issue Record Found for this User.");
            new HomePage().menu(role, uId);
            return;
        }

        // Step 2: Get book ID from DAO
        int bid = new Admin_Dao_Impl().getBookDetailsByBookName(bName);
        if (bid == -1) {
            System.out.println("Book not found.");
            new HomePage().menu(role, uId);
            return;
        }

        // Step 3: Calculate fine based on due date
        int fineDays = new Admin_Dao_Impl().calculateFine(issueId);
        double fine = fineDays > 0 ? fineDays * 5.0 : 0.0;

        // Step 4: Insert return record using DAO
        boolean isReturnInserted = new Admin_Dao_Impl().insertReturnBookRecord(issueId, fine);
        if (isReturnInserted) {
            System.out.println("Fine is = " + fine);

            // Step 5: Update book status to 'Returned' using DAO
            boolean isStatusUpdated = new Admin_Dao_Impl().updateBookStatusToReturned(issueId);

            // Step 6: Update book quantity using DAO
            boolean isQuantityUpdated = new Admin_Dao_Impl().updateBookQuantity(bid);

            if (isStatusUpdated) {
                System.out.println("Book return successfully.");
                if (isQuantityUpdated) {
                    System.out.println("Book quantity updated successfully.");
                } else {
                    System.out.println("Problem occurred while updating book quantity.");
                }
            } else {
                System.out.println("Failed to update issued book status.");
            }
        } else {
            System.out.println("Failed to insert return record.");
        }

        new HomePage().menu(role, uId);
    }
}
