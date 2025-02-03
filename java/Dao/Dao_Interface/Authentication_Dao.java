package Dao.Dao_Interface;

import java.sql.SQLException;

public interface Authentication_Dao
{
    int insertAdditionalInfo(String fName, String lName, String email, String mobile, String adhar, String uId) throws SQLException;
    boolean validateLogin(String role, String uId, String password) throws SQLException;
    boolean checkAvailableIssueRecord(String uId);
    boolean isUserExists(String uId) throws SQLException;
    boolean SignUpUser(String uId, String pass, String cPass) throws SQLException;
}
