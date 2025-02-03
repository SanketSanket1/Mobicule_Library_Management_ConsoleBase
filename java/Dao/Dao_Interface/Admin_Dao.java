package Dao.Dao_Interface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface Admin_Dao
{
    boolean isPermittedDao(String uId, String pass, String cPass);
    ResultSet issueCountDao(String userId) throws SQLException;
    LocalDate dueDate();
    boolean issueBookDao(String userId, int isbn, LocalDate returnDate);
    int getIssueIdByUserName(String uName, String bNAme);
    int getBookDetailsByBookName(String bName);
    int calculateFine(int issueId);
    boolean insertReturnBookRecord(int issueId, double fine);
    boolean updateBookStatusToReturned(int issueId);
    boolean updateBookQuantity(int bid);
    ResultSet getBooksSortedByName(String order);
    int getBookCount();
    boolean deleteBookByISBN(int isbn);
    boolean updateBookInfo(int bid, String column, String newValue);
    boolean checkUniqueIsbn(int isbn);
    boolean updateBookQuantity(int bid, int quantity);
    boolean insertBook(int isbn, String bName, String aName, int nop, String category, int quantity);
    ResultSet getDistinctCategories();
    ResultSet getAllStudentsAsc() throws SQLException;
    ResultSet getAllStudentsDesc() throws SQLException;
    int getStudentCount() throws SQLException;
    int deleteStudentByUserId(String userId) throws SQLException;
    int deleteStudentFromStudentTable(String userId) throws SQLException;
    int updateStudentInfo(String userId, String field, String value) throws SQLException;
    ResultSet getAllBooks() throws SQLException;
    ResultSet getDefaulterList() throws SQLException;
    ResultSet getAllStudents() throws SQLException;

}
