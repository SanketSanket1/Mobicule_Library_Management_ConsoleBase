package Admin_Side.Admin_Side_Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ViewStudentList
{
    void viewStudent(String role,String uId);
    void printStudentDetails(ResultSet rs) throws SQLException;
}
