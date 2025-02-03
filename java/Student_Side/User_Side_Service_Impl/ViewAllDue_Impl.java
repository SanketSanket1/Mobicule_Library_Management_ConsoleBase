package Student_Side.User_Side_Service_Impl;

import Dao.Dao_Impl.User_Dao_Impl;
import Student_Side.User_Side_Service.ViewAllDue;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAllDue_Impl implements ViewAllDue {

    @Override
    public ResultSet viewDue(String uId) {
        try {
            return new User_Dao_Impl().getDueBooks(uId);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching due books", e);
        }
    }
}
