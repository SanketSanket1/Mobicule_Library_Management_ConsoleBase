package Admin_Side.Admin_Side_Controller;

import Admin_Side.Admin_Side_Service_Impl.ViewDefaulter_Impl;

public class ViewDefaulter_Controller
{
    public void handleViewDefaulterList(String role, String uId)
    {
        // Delegate to the service layer to view the defaulter list
        new ViewDefaulter_Impl().viewDefaulterList(role, uId);
    }
}


