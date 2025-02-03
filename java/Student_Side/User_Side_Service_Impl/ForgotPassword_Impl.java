package Student_Side.User_Side_Service_Impl;

import Dao.Dao_Impl.User_Dao_Impl;
import Main.HomePage;
import Student_Side.User_Side_Service.ForgotPassword;
import Validations.EcryptionDecryption;

public class ForgotPassword_Impl implements ForgotPassword
{
    public void forgot(String role, String userId, String uId, String adhar, String newPassword)
    {
        if (new User_Dao_Impl().isAbleToForgot(uId, adhar)) {
            try {
                // Encrypt the new password before storing
                String encryptedPassword = EcryptionDecryption.encryptPassword(newPassword);

                // Update password in database
                int result = new User_Dao_Impl().forgotPassword(uId, encryptedPassword);

                if (result > 0) {
                    System.out.println("Password Updated Successfully.");
                    new HomePage().menu(role, userId);
                } else {
                    System.out.println("Some Problem Occurred.");
                }
            } catch (Exception e) {
                throw new RuntimeException("Error occurred while updating password", e);
            }
        } else {
            System.out.println("Unable to reset password. Incorrect User ID or Adhar Card.");
        }
    }
}
