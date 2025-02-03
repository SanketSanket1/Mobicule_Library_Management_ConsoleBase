package Admin_Side.Admin_Side_Controller;

public class ViewBookList_Controller
{

    public void handleViewBooks(String role, String uId)
    {
        // Delegate to the service layer to view the books
        new Admin_Side.Admin_Side_Service_Impl.ViewBookList_Impl().viewBook(role,uId);
    }
}

