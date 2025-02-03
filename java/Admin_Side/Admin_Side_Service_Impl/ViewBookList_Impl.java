package Admin_Side.Admin_Side_Service_Impl;

import Admin_Side.Admin_Side_Service.ViewBookList;
import Main.HomePage;

public class ViewBookList_Impl extends ShowBook_Impl implements ViewBookList
{
    public void viewBook(String role,String uId)
    {
        try
        {
            System.out.println("\n\nBOOK TABLE :-");
            System.out.println(" ______________________________________________________________________________________________________________________________________________");
            showBook();
            new HomePage().menu(role,uId);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
