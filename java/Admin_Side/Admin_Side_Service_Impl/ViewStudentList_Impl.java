package Admin_Side.Admin_Side_Service_Impl;

import Admin_Side.Admin_Side_Service.ViewStudentList;
import Dao.Dao_Impl.Admin_Dao_Impl;
import Main.HomePage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewStudentList_Impl implements ViewStudentList
{

    @Override
    public void viewStudent(String role, String uId) {
        try {
            ResultSet rs = new Admin_Dao_Impl().getAllStudents();
            printStudentDetails(rs);
            new HomePage().menu(role, uId);
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching students", e);
        }
    }

    public void printStudentDetails(ResultSet rs) throws SQLException {
        System.out.println("\nSTUDENT TABLE :-");
        System.out.println(" __________________________________________________________________________________________________________________________");
        System.out.printf("|  %-10s |  %-15s |  %-15s |  %-25s |  %-20s |  %-15s|\n", "ID", "First Name", "Last Name", "Email", "Mobile", "Adhar Card");
        System.out.println("|__________________________________________________________________________________________________________________________|");

        while (rs.next()) {
            int id = rs.getInt(1);
            String fname = rs.getString(2);
            String lname = rs.getString(3);
            String email = rs.getString(4);
            String mobile = rs.getString(5);
            String adhar = rs.getString(6);

            System.out.printf("|  %-10s |  %-15s |  %-15s |  %-25s |  %-20s |  %-15s|\n", id, fname, lname, email, mobile, adhar);
        }

        System.out.println("|__________________________________________________________________________________________________________________________|");
    }
}
