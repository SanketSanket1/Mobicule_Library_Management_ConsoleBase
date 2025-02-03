package Admin_Side.Admin_Side_Service_Impl;

import Admin_Side.Admin_Side_Service.ViewDefaulter;
import Dao.Dao_Impl.Admin_Dao_Impl;
import Main.HomePage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewDefaulter_Impl implements ViewDefaulter
{
    @Override
    public void viewDefaulterList(String role, String uId)
    {
        try
        {
            ResultSet rs = new Admin_Dao_Impl().getDefaulterList();
            printDefaulterDetails(rs);
            new HomePage().menu(role, uId);
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error while fetching defaulters", e);
        }
    }
    public void printDefaulterDetails(ResultSet rs) throws SQLException {
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", "Student ID", "User ID", "First Name", "Last Name", "Book ID", "Book Name");
        while (rs.next()) {
            int sId = rs.getInt(1);
            String userid = rs.getString(2);
            String fname = rs.getString(3);
            String lname = rs.getString(4);
            int bId = rs.getInt(5);
            String bname = rs.getString(6);

            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", sId, userid, fname, lname, bId, bname);
        }
    }
}
