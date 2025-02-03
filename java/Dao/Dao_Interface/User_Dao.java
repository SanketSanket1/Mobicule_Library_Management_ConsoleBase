package Dao.Dao_Interface;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface User_Dao
{
    ResultSet getIssuedBooks(String uName) throws SQLException;
    ResultSet getDueBooks(String uName) throws SQLException;
    ResultSet getStudentRecord(String uId);
    int insertDeletedRecord(String fName,String lName,String email,String mobile,String adhar,String uId);
    int deleteAccount(String uId);
    boolean isAbleToForgot(String uId, String adhar);
    int forgotPassword(String uId, String pass);
}
