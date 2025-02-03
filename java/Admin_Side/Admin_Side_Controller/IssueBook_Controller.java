package Admin_Side.Admin_Side_Controller;

import Admin_Side.Admin_Side_Service.IssueBook_Service;
import Admin_Side.Admin_Side_Service_Impl.IssueBook_Service_Impl;

import java.util.Scanner;

public class IssueBook_Controller {

    private IssueBook_Service issueBookService;

    // Constructor
    public IssueBook_Controller() {
        issueBookService = new IssueBook_Service_Impl();
    }

    public void handleIssueBook(String role, String uId) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n\nEnter Your UserId : ");
        System.out.print("=> ");
        String userId = sc.next();

        System.out.println("\n\nEnter Book ISBN Number : ");
        System.out.print("=> ");
        int isbn = sc.nextInt();

        // Call the service layer method
        issueBookService.issueNewBook(role, uId, userId, isbn);
    }
}
