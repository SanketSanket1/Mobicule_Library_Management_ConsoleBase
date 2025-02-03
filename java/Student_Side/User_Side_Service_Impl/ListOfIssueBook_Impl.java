package Student_Side.User_Side_Service_Impl;

import Dao.Dao_Impl.User_Dao_Impl;
import Student_Side.User_Side_Service.ListOfIssueBook;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListOfIssueBook_Impl implements ListOfIssueBook
{

    @Override
    public ResultSet listIssueBook(String uId) {
        try {
            return new User_Dao_Impl().getIssuedBooks(uId);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching issued books", e);
        }
    }
}
