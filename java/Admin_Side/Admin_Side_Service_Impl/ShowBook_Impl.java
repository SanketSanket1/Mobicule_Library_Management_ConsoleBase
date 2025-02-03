package Admin_Side.Admin_Side_Service_Impl;

import Admin_Side.Admin_Side_Service.ShowBook;
import Dao.Dao_Impl.Admin_Dao_Impl;

import java.sql.*;

public class ShowBook_Impl implements ShowBook
{

    public void showBook()
    {
        try
        {
            ResultSet rs = new Admin_Dao_Impl().getAllBooks();
            printBookDetails(rs);
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error while fetching books", e);
        }
    }

    public void printBookDetails(ResultSet rs) throws SQLException
    {
        System.out.printf("|   %-10s |  %-15s |  %-15s |  %-25s |  %-20s |  %-15s |  %-15s|\n", "id", "ISBN Number", "Book Name", "Author Name", "No Of Pages", "Category", "Quantity");
        System.out.println("|______________________________________________________________________________________________________________________________________________|");
        while (rs.next())
        {
            int id = rs.getInt(1);
            int isbn = rs.getInt(2);
            String bname = rs.getString(3);
            String author = rs.getString(4);
            int no_of_page = rs.getInt(5);
            String category = rs.getString(6);
            int quantity = rs.getInt(7);

            System.out.printf("|   %-10s |  %-15s |  %-15s |  %-25s |  %-20s |  %-15s |  %-15s|\n", id, isbn, bname, author, no_of_page, category, quantity);
        }
        System.out.println("|______________________________________________________________________________________________________________________________________________|");
    }
}

