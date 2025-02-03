package Dao.Dao_Impl;

import Dao.Dao_Interface.Authentication_Dao;
import Database.DB_Connection;
import Validations.EcryptionDecryption;
import java.sql.*;


public class Authentication_Dao_Impl implements Authentication_Dao
{
    static java.sql.Connection connection;
    static ResultSet resultSet;
    static PreparedStatement preparedStatement;


    //Additional_Info
    public int insertAdditionalInfo(String fName, String lName, String email, String mobile, String adhar, String uId) throws SQLException {
        connection = DB_Connection.getSQLConnection();
        String query = "INSERT INTO sanket_I1433_UserData (fname, lname, email, mobile, adhar, userId, Activate) VALUES(?,?,?,?,?,?,'T')";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, fName);
        preparedStatement.setString(2, lName);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, mobile);
        preparedStatement.setString(5, adhar);
        preparedStatement.setString(6, uId);
        return preparedStatement.executeUpdate();
    }


    //Login
    public boolean validateLogin(String role, String uId, String password) throws SQLException
    {
        String pass = "";

        connection = DB_Connection.getSQLConnection();

        if (role.equals("admin")) {
            String query = "SELECT * FROM Sanket_I1433_Admin WHERE a_userId = ? AND a_pass = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, uId);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            return resultSet.next();  // If a record is found, the login is valid
        }

        if (role.equals("student"))
        {
            try
            {
                String query = "SELECT password FROM Sanket_I1433_Student WHERE userId = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, uId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                {
                    String encryptedPassword = resultSet.getString(1);

                    pass = EcryptionDecryption.decryptPassword(encryptedPassword);
                    return password.equals(pass);  // Compare the decrypted password with the input password
                }
            } catch (SQLException e)
            {

                throw new RuntimeException("Database error occurred", e);
            }
            catch (Exception e)
            {

                throw new RuntimeException("Decryption failed for userId: " + uId, e);
            }
        }
        return false;  // Return false if no matching user is found or decryption fails

    }


    //Check Issue Record
    public boolean checkAvailableIssueRecord(String uId)
    {
        try
        {
            connection = DB_Connection.getSQLConnection();
            preparedStatement = connection.prepareStatement("select issue_id from Sanket_I1433_IssuedBook where student_id = (select id from Sanket_I1433_UserData where userId = ?)");
            preparedStatement.setString(1,uId);
            resultSet = preparedStatement.executeQuery();

            return !resultSet.next();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    //Signup

        // Check if the user already exists
        public boolean isUserExists(String uId) throws SQLException
        {
            connection = DB_Connection.getSQLConnection();
            String query = "SELECT userId FROM Sanket_I1433_student WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, uId);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();  // If a record is found, the user exists
        }

        // Insert a new user
        public boolean SignUpUser(String uId, String pass, String cPass) throws SQLException
        {
            connection = DB_Connection.getSQLConnection();
            String query = "INSERT INTO Sanket_I1433_student (userId, password, confirmPass, created_time, Activate) VALUES (?, ?, ?, current_TimeStamp,'T')";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, uId);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, cPass);

            int res = preparedStatement.executeUpdate();
            return res > 0;  // If the insert was successful
        }
}
