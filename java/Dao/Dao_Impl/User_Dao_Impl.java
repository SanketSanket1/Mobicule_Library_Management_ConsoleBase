package Dao.Dao_Impl;

import Dao.Dao_Interface.User_Dao;
import Database.DB_Connection;

import java.sql.*;

public class User_Dao_Impl implements User_Dao
{
    Connection connection;
    PreparedStatement preparedStatement;


    //ListOfIssueBook
    public ResultSet getIssuedBooks(String uName) throws SQLException
    {
        connection = DB_Connection.getSQLConnection();
        String query = "SELECT b.bname, i.due_date, i.status " +
                "FROM Sanket_I1433_IssuedBook as i " +
                "INNER JOIN Sanket_I1433_Book as b ON i.book_id = b.id " +
                "WHERE i.student_id = (SELECT id FROM sanket_I1433_UserData as u WHERE u.userId = ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, uName);
        return preparedStatement.executeQuery();
    }


    //ViewAllDue
    public ResultSet getDueBooks(String uName) throws SQLException
    {
        connection = DB_Connection.getSQLConnection();
        String query = "SELECT b.bname, i.due_date " +
                "FROM Sanket_I1433_IssuedBook as i " +
                "INNER JOIN Sanket_I1433_Book as b ON i.book_id = b.id " +
                "AND status = 'Issued' " +
                "AND current_TimeStamp >= due_date " +
                "WHERE i.student_id = (SELECT id FROM sanket_I1433_UserData as u WHERE u.userId = ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, uName);
        return preparedStatement.executeQuery();
    }



    //select record for perticular userid
    public ResultSet getStudentRecord(String uId)
    {
        try
        {
            connection = DB_Connection.getSQLConnection();
            preparedStatement = connection.prepareStatement("select * from Sanket_I1433_userdata where userid = ?");
            preparedStatement.setString(1,uId);
            return preparedStatement.executeQuery();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //Insert Delete Account Data in Another Table
    public int insertDeletedRecord(String fName,String lName,String email,String mobile,String adhar,String uId)
    {
        int res =0 ;
        try
        {
            connection = DB_Connection.getSQLConnection();

            preparedStatement = connection.prepareStatement("insert into Sanket_I1433_DeletedUser values(?,?,?,?,?,?)");
            preparedStatement.setString(1,fName);
            preparedStatement.setString(2,lName);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,mobile);
            preparedStatement.setString(5,adhar);
            preparedStatement.setString(6,uId);

             res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return res;
    }


    //Delete Account
    public int deleteAccount(String uId)
    {
        try
        {
            connection = DB_Connection.getSQLConnection();
            preparedStatement = connection.prepareStatement("delete from sanket_I1433_Student where userid = ?");
            preparedStatement.setString(1,uId);

            int res = preparedStatement.executeUpdate();


            preparedStatement = connection.prepareStatement("delete from sanket_I1433_UserData where userid = ?");
            preparedStatement.setString(1,uId);

            int res1 = preparedStatement.executeUpdate();

            if(res > 0 && res1 > 0)
                return 1;
            else
                return 0;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    //Forgot Password
    public boolean isAbleToForgot(String uId, String adhar)
    {
        try
        {
            connection = DB_Connection.getSQLConnection();
            preparedStatement = connection.prepareStatement("select * from Sanket_I1433_UserData where userId = ? And adhar = ?");
            preparedStatement.setString(1,uId);
            preparedStatement.setString(2,adhar);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int forgotPassword(String uId, String pass)
    {
        try
        {
            connection = DB_Connection.getSQLConnection();
            preparedStatement = connection.prepareStatement("update Sanket_I1433_Student set password = ? ,confirmPass = ? where userId = ?");
            preparedStatement.setString(1,pass);
            preparedStatement.setString(2,pass);
            preparedStatement.setString(3,uId);

            return preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
