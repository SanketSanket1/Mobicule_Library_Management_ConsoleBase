package Dao.Dao_Impl;

import Dao.Dao_Interface.Admin_Dao;
import Database.DB_Connection;

import java.sql.*;
import java.time.LocalDate;

public class Admin_Dao_Impl implements Admin_Dao {
    java.sql.Connection connection;
    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;


    //isPermitted
    public boolean isPermittedDao(String uId, String pass, String cPass)
    {
        try
        {
            // Establish the connection
            connection = DB_Connection.getSQLConnection();

            // SQL query to check if user with the same userId and password exists
            String query = "SELECT COUNT(*) FROM sanket_I1433_student WHERE userId = ? AND password = ? AND confirmpass = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, uId);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, cPass);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                //this statement returns true when result set contains record
                return resultSet.getInt(1) > 0;
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return false;
    }


    //count id for issue book
    public ResultSet issueCountDao(String userId)throws SQLException
    {
            connection = DB_Connection.getSQLConnection();
            String query = "select count(student_id) from Sanket_I1433_IssuedBook where student_id = (select id from sanket_I1433_UserData  \n" +
                    "where userid = ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userId);
            return preparedStatement.executeQuery();
    }



    //find Due Date (6 months from issue date)
    public LocalDate dueDate()
    {
        try
        {
            connection = DB_Connection.getSQLConnection();
            preparedStatement = connection.prepareStatement("SELECT DATEADD(MONTH, 1, CAST(GETDATE() AS DATE)) AS DateAfterSixMonths;");
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                return resultSet.getDate("DateAfterSixMonths").toLocalDate();
            }
            else
            {
                throw new RuntimeException("Problem Occur in Find Due Date");
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    //issueNewBook
    public boolean issueBookDao(String userId, int isbn, LocalDate returnDate)
    {
        java.sql.Connection con;
        PreparedStatement pst;
        ResultSet rs;

        try
        {
            con = DB_Connection.getSQLConnection();

            // Check book quantity
            String checkQuantityQuery = "SELECT id, book_quantity FROM Sanket_I1433_book WHERE isbn = ?";
            pst = con.prepareStatement(checkQuantityQuery);
            pst.setInt(1, isbn);
            rs = pst.executeQuery();

            int bookId = -1;
            int quantity = 0;
            if (rs.next())
            {
                bookId = rs.getInt("id");
                quantity = rs.getInt("book_quantity");
            }

            if (bookId == -1 || quantity <= 0)
            {
                System.out.println("Book not available or quantity is zero.");
                return false; // Book not available
            }

            // Get user ID
            String getUserIdQuery = "SELECT id FROM Sanket_I1433_UserData WHERE userid = ?";
            pst = con.prepareStatement(getUserIdQuery);
            pst.setString(1, userId);
            rs = pst.executeQuery();

            int userIdInt = -1;
            if (rs.next())
            {
                userIdInt = rs.getInt("id");
            }

            if (userIdInt == -1)
            {
                System.out.println("User not found!");
                return false; // User not found
            }

            // Insert issued book record
            String insertIssuedBookQuery = "INSERT INTO Sanket_I1433_IssuedBook (student_id, book_id, issue_date, due_date, status) VALUES (?, ?, current_TimeStamp, ?, ?)";
            pst = con.prepareStatement(insertIssuedBookQuery);
            pst.setInt(1, userIdInt);
            pst.setInt(2, bookId);
            pst.setDate(3, Date.valueOf(returnDate));
            pst.setString(4, "Issued");

            int rowsInserted = pst.executeUpdate();

            if (rowsInserted > 0)
            {
                // Update book quantity
                String updateQuantityQuery = "UPDATE Sanket_I1433_book SET book_quantity = ? WHERE id = ?";
                pst = con.prepareStatement(updateQuantityQuery);
                pst.setInt(1, quantity - 1);
                pst.setInt(2, bookId);

                pst.executeUpdate();
                return true; // Book issued successfully
            }
            else
            {
                System.out.println("Failed to issue book.");
                return false; // Insertion failed
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error while issuing the book", e);

        }
    }


    //ReturnBook
    public int getIssueIdByUserName(String uName, String bNAme) {
        int issueId = -1;
        try {
            Connection con = DB_Connection.getSQLConnection();
            String query = "SELECT issue_id FROM Sanket_I1433_IssuedBook WHERE student_id = " +
                    "(SELECT id FROM Sanket_I1433_UserData WHERE userId = ?) and book_id = (select id from sanket_I1433_book where bname = ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, uName);
            pst.setString(2, bNAme);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                issueId = rs.getInt("issue_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issueId;
    }

    public int getBookDetailsByBookName(String bName) {
        int bid = -1;
        try (java.sql.Connection con = DB_Connection.getSQLConnection()) {
            String query = "SELECT id, isbn FROM Sanket_I1433_book WHERE bname = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, bName);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                bid = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bid;
    }

    public int calculateFine(int issueId) {
        int fineDays = 0;
        try (java.sql.Connection con = DB_Connection.getSQLConnection()) {
            String query = "SELECT DATEDIFF(DAY, due_date, current_TimeStamp) AS 'difference' FROM Sanket_I1433_IssuedBook WHERE issue_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, issueId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                fineDays = rs.getInt("difference");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fineDays;
    }

    public boolean insertReturnBookRecord(int issueId, double fine)
    {
        try (java.sql.Connection con = DB_Connection.getSQLConnection()) {
            String query = "INSERT INTO Sanket_I1433_ReturnBook (issue_id, return_date, fine_amount) VALUES (?, current_TimeStamp, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, issueId);
            pst.setDouble(2, fine);

            int res = pst.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateBookStatusToReturned(int issueId)
    {
        try (java.sql.Connection con = DB_Connection.getSQLConnection()) {
            String query = "UPDATE Sanket_I1433_IssuedBook SET status = 'Returned' WHERE issue_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, issueId);

            int res = pst.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateBookQuantity(int bid)
    {
        try (java.sql.Connection con = DB_Connection.getSQLConnection()) {
            String query = "UPDATE Sanket_I1433_book SET book_quantity = book_quantity + 1 WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, bid);

            int res = pst.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    //Manage_Book
    public ResultSet getBooksSortedByName(String order)
    {
        ResultSet rs = null;
        try {
            java.sql.Connection con = DB_Connection.getSQLConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Sanket_I1433_Book ORDER BY bname " + order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

        public int getBookCount()
        {
            int count = 0;
            try
            {
                java.sql.Connection con = DB_Connection.getSQLConnection();
                PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM Sanket_I1433_Book");
                ResultSet rs = pst.executeQuery();
                if (rs.next())
                {
                    count = rs.getInt(1);
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return count;
        }

        public boolean deleteBookByISBN(int isbn)
        {
            try
            {
                java.sql.Connection con = DB_Connection.getSQLConnection();
                PreparedStatement pst = con.prepareStatement("DELETE FROM Sanket_I1433_Book WHERE isbn = ?");
                pst.setInt(1, isbn);
                int res = pst.executeUpdate();
                return res > 0;
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return false;
        }

        public boolean updateBookInfo(int bid, String column, String newValue)
        {
            String query = "UPDATE Sanket_I1433_Book SET " + column + " = ? WHERE isbn = ?";
            try
            {
                java.sql.Connection con = DB_Connection.getSQLConnection();
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, newValue);
                pst.setInt(2, bid);
                int res = pst.executeUpdate();
                return res > 0;
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return false;
        }

        public boolean checkUniqueIsbn(int isbn)
        {
            try
            {
                java.sql.Connection con = DB_Connection.getSQLConnection();
                PreparedStatement pst = con.prepareStatement("select isbn from Sanket_I1433_Book where isbn = ?");
                pst.setInt(1,isbn);
                ResultSet rs = pst.executeQuery();

                if(rs.next())
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }

        public boolean updateBookQuantity(int bid, int quantity)
        {
            try
            {
                java.sql.Connection con = DB_Connection.getSQLConnection();
                PreparedStatement pst = con.prepareStatement("UPDATE Sanket_I1433_Book SET book_quantity = ? WHERE id = ?");
                pst.setInt(1, quantity);
                pst.setInt(2, bid);
                int res = pst.executeUpdate();
                return res > 0;
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return false;
        }

        public boolean insertBook(int isbn, String bName, String aName, int nop, String category, int quantity)
        {
            try
            {
                java.sql.Connection con = DB_Connection.getSQLConnection();
                PreparedStatement pst = con.prepareStatement("INSERT INTO Sanket_I1433_Book (isbn, bname, author, no_of_page, btype, book_quantity) VALUES (?,?,?,?,?,?)");
                pst.setInt(1, isbn);
                pst.setString(2, bName);
                pst.setString(3, aName);
                pst.setInt(4, nop);
                pst.setString(5, category);
                pst.setInt(6,quantity);
                int res = pst.executeUpdate();
                return res > 0;
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return false;
        }

        public ResultSet getDistinctCategories()
        {
            ResultSet rs = null;
            try
            {
                java.sql.Connection con = DB_Connection.getSQLConnection();
                Statement st = con.createStatement();
                rs = st.executeQuery("SELECT DISTINCT(btype) FROM Sanket_I1433_Book");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return rs;
        }



    //Manage_Student

        public ResultSet getAllStudentsAsc() throws SQLException
        {
            connection = DB_Connection.getSQLConnection();
            Statement st = connection.createStatement();
            return st.executeQuery("SELECT * FROM Sanket_I1433_userData ORDER BY fname ASC");
        }

        public ResultSet getAllStudentsDesc() throws SQLException
        {
            connection = DB_Connection.getSQLConnection();
            Statement st = connection.createStatement();
            return st.executeQuery("SELECT * FROM Sanket_I1433_userData ORDER BY fname DESC");
        }

        public int getStudentCount() throws SQLException
        {
            connection = DB_Connection.getSQLConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT COUNT(*) FROM Sanket_I1433_userData");
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {
                return rs.getInt(1);
            }
            return 0;
        }




        public int deleteStudentByUserId(String userId) throws SQLException
        {
            connection = DB_Connection.getSQLConnection();
            PreparedStatement pst = connection.prepareStatement("delete from Sanket_I1433_userData WHERE userId = ?");
            pst.setString(1, userId);
            return pst.executeUpdate();
        }

        public int deleteStudentFromStudentTable(String userId) throws SQLException
        {
            connection = DB_Connection.getSQLConnection();
            PreparedStatement pst = connection.prepareStatement("delete from Sanket_I1433_student WHERE userId = ?");
            pst.setString(1, userId);
            return pst.executeUpdate();
        }

        public int updateStudentInfo(String userId, String field, String value) throws SQLException
        {
            connection = DB_Connection.getSQLConnection();
            PreparedStatement pst = connection.prepareStatement("UPDATE Sanket_I1433_userData SET " + field + " = ? WHERE userId = ?");
            pst.setString(1, value);
            pst.setString(2, userId);
            return pst.executeUpdate();
        }


    //ShowBook
    public ResultSet getAllBooks() throws SQLException
    {
            connection = DB_Connection.getSQLConnection();
            Statement st = connection.createStatement();
            return st.executeQuery("SELECT * FROM Sanket_I1433_Book");
    }


    //ViewDefaulter
    public ResultSet getDefaulterList() throws SQLException
    {
        connection = DB_Connection.getSQLConnection();
        String query = "SELECT \n" +
                "    s.id AS student_id, \n" +
                "    s.userid, \n" +
                "    s.fname, \n" +
                "    s.lname, \n" +
                "    b.id AS book_id, \n" +
                "    b.bname, \n" +
                "    i.issue_date, \n" +
                "    i.due_date\n" +
                "FROM \n" +
                "    sanket_I1433_userdata AS s\n" +
                "JOIN \n" +
                "    Sanket_I1433_IssuedBook AS i ON s.id = i.student_id\n" +
                "JOIN \n" +
                "    sanket_I1433_book AS b ON b.id = i.book_id\n" +
                "WHERE \n" +
                "    i.due_date < CURRENT_TIMESTAMP  ";
        PreparedStatement pst = connection.prepareStatement(query);
        return pst.executeQuery();
    }


    //ViewStudentList
    public ResultSet getAllStudents() throws SQLException
    {
        connection = DB_Connection.getSQLConnection();
        String query = "SELECT * FROM Sanket_I1433_userData";
        PreparedStatement pst = connection.prepareStatement(query);
        return pst.executeQuery();
    }

}




