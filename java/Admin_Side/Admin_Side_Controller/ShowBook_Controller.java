package Admin_Side.Admin_Side_Controller;

import Admin_Side.Admin_Side_Service_Impl.ShowBook_Impl;

public class ShowBook_Controller
{
    public void handleShowBooks()
    {
        // Delegate to the service layer to show books
        new ShowBook_Impl().showBook();
    }
}


