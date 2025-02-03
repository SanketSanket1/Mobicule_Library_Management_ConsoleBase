package Student_Side.User_Side_Service_Impl;

import Dao.Dao_Impl.Authentication_Dao_Impl;
import Dao.Dao_Impl.User_Dao_Impl;
import Student_Side.User_Side_Service.DeleteAccount;

import java.sql.ResultSet;

public class DeleteAccount_Impl implements DeleteAccount
{
    @Override
    public boolean deleteAccount(String role, String uId, String pass)
    {
        try
        {
            Authentication_Dao_Impl authDao = new Authentication_Dao_Impl();
            User_Dao_Impl userDao = new User_Dao_Impl();

            // Validate user credentials and ensure no pending book issues
            if (authDao.validateLogin(role, uId, pass) && authDao.checkAvailableIssueRecord(uId))
            {
                ResultSet resultSet = new User_Dao_Impl().getStudentRecord(uId);
                if(resultSet.next())
                {
                    String fName = resultSet.getString(2);
                    String lName = resultSet.getString(3);
                    String email = resultSet.getString(4);
                    String mobile = resultSet.getString(5);
                    String adhar = resultSet.getString(6);

                    int isInsert = new User_Dao_Impl().insertDeletedRecord(fName,lName,email,mobile,adhar,uId);

                    int rs = 0;
                    if(isInsert == 1)
                    {
                        rs = userDao.deleteAccount(uId);
                    }
                    return rs > 0; // Return true if account deleted successfully
                }
                else
                {
                    System.out.println("Problem Occur.");
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false; // Return false if deletion failed
    }
}
