package Admin_Side.Admin_Side_Service;

public interface Manage_Book
{
        void ascSortBookList(String role, String uId);
        void descSortBookList(String role, String uId);
        void countBook(String role, String uId);
        void removeBookInfo(String role, String uId);
        void updateBookInfo(String role, String uId);
        void insertBookInfo(String role, String uId);
        void categoryBookInfo(String role, String uId);
}


