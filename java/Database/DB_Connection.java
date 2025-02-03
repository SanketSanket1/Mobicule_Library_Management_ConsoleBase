package Database;

import java.sql.*;

public class DB_Connection
{
    static java.sql.Connection con;
    static Statement st;
    static ResultSet rs;

    public static java.sql.Connection getSQLConnection()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://10.1.1.196;databaseName=TRAINING_DB","sa","M0b1cule!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
}
