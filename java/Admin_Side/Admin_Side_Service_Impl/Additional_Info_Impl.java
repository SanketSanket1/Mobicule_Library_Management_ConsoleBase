package Admin_Side.Admin_Side_Service_Impl;

import Admin_Side.Admin_Side_Service.Additional_Info;
import Dao.Dao_Impl.Authentication_Dao_Impl;
import java.sql.SQLException;

public class Additional_Info_Impl implements Additional_Info
{

    @Override
    public boolean insertAdditionalInfo(String fName, String lName, String email, String mobile, String adhar, String uId)
    {
        try
        {
            int res = new Authentication_Dao_Impl().insertAdditionalInfo(fName, lName, email, mobile, adhar, uId);
            return res > 0;
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error while inserting additional info", e);
        }
    }
}
