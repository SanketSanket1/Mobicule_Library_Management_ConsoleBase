package Admin_Side.Admin_Side_Controller;


import Admin_Side.Admin_Side_Service_Impl.ReturnBook_Impl;

import Validations.NameValidation;

import java.util.Scanner;

public class ReturnBook_Controller {

    ReturnBook_Impl returnBookService;

    // Constructor
    public ReturnBook_Controller() {
        returnBookService = new ReturnBook_Impl();
    }

    public void handleReturnBook(String role, String uId) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter User Name : ");
        System.out.print("=> ");
        String uName = sc.next();
        uName = new NameValidation().checkNameValidation(uName);

        System.out.println("Enter Book Name : ");
        System.out.print("=> ");
        String bName = sc.next().trim();
        bName = new NameValidation().checkNameValidation(bName);

        // Delegate to service layer to handle the book return logic
        returnBookService.returnBook(uName, bName, role, uId);
    }
}

