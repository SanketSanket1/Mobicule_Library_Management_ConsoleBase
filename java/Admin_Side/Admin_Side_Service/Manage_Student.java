package Admin_Side.Admin_Side_Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Manage_Student
{
    void sortStudentListAsc(String role,String uId);
    void sortStudentListDesc(String role,String uId);
    void countStudents(String role,String uId);
    void removeStudent(String role,String uId);
    void updateStudentInfo(String role,String uId);
    void insertStudentInfo(String role,String uId);
    void printStudentList(ResultSet rs) throws SQLException;

}
