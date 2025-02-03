package Admin_Side.Admin_Side_Controller;

import Admin_Side.Admin_Side_Service_Impl.ViewStudentList_Impl;

public class ViewStudentList_Controller
{
    public void handleViewStudentList(String role, String uId) {
        new ViewStudentList_Impl().viewStudent(role, uId);
    }
}

