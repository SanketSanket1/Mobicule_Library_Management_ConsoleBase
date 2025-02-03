package Admin_Side.Admin_Side_Controller;

import Admin_Side.Admin_Side_Service.Manage_Student;
import Admin_Side.Admin_Side_Service_Impl.Manage_Student_Impl;
import Validations.IntInputValidation;

import java.util.Scanner;

public class Manage_Student_Controller {

    private Manage_Student manageStudentService;

    // Constructor
    public Manage_Student_Controller() {
        manageStudentService = new Manage_Student_Impl();
    }

    public void handleStudentOperations(String role, String uId) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose an operation: ");
        System.out.println("1 -> Sort Students List (Asc)");
        System.out.println("2 -> Sort Students List (Desc)");
        System.out.println("3 -> Count Students");
        System.out.println("4 -> Remove Student");
        System.out.println("5 -> Update Student Info");
        System.out.println("6 -> Insert New Student");

        System.out.print("Enter your choice: ");
        int choice = new IntInputValidation().checkInt();

        switch (choice) {
            case 1:
                manageStudentService.sortStudentListAsc(role, uId);
                break;
            case 2:
                manageStudentService.sortStudentListDesc(role, uId);
                break;
            case 3:
                manageStudentService.countStudents(role, uId);
                break;
            case 4:
                manageStudentService.removeStudent(role, uId);
                break;
            case 5:
                manageStudentService.updateStudentInfo(role, uId);
                break;
            case 6:
                manageStudentService.insertStudentInfo(role, uId);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                handleStudentOperations(role, uId); // Recurse if invalid
                break;
        }
    }
}

