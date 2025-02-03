package Admin_Side.Admin_Side_Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ShowBook
{
    void showBook();
    void printBookDetails(ResultSet rs) throws SQLException;
}
