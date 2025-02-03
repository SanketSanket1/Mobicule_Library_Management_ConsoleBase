package Validations;

import Dao.Dao_Impl.Admin_Dao_Impl;
import java.util.regex.Pattern;

public class Check_Authentication extends Admin_Dao_Impl
{

    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    public boolean isPermitted(String uId, String pass, String cPass)
    {

        try
        {
            boolean isExist = isPermittedDao(uId, pass, cPass);

            if (isExist)
            {
                // User exists with the same credentials, return false
                return false;
            }

            // If the password is valid based on the regex return true
            return Pattern.matches(PASSWORD_REGEX, pass);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
//
//
//
//package Validations;
//
//import Dao.Dao_Interface.Admin_Dao;
//import java.util.regex.Pattern;
//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import java.util.Base64;
//import Main.Signup;
//
//public class Check_Authentication extends Admin_Dao
//{
//    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
//    private static final SecretKey secretKey;
//
//    static {
//        try {
//            secretKey = Signup.getSecretKey(); // Retrieve AES key from Signup class
//        } catch (Exception e) {
//            throw new RuntimeException("Error initializing encryption key", e);
//        }
//    }
//
//    private static String decrypt(String encryptedData) throws Exception {
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);
//        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
//        return new String(decryptedBytes);
//    }
//
//    public boolean isPermitted(String uId, String encryptedPass, String encryptedCPass)
//    {
//        try
//        {
//            // Decrypt passwords before checking
//            String pass = decrypt(encryptedPass);
//            String cPass = decrypt(encryptedCPass);
//
//            // Check password format
//            return Pattern.matches(PASSWORD_REGEX, pass);
//        }
//        catch (Exception e)
//        {
//            throw new RuntimeException(e);
//        }
//    }
//}
//
