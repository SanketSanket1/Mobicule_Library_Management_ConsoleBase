package Validations;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;
import java.security.SecureRandom;

public class EcryptionDecryption
{
    // Secret Key (Use your secure method to store and retrieve keys)
    public static SecretKey secretKey;

    static
    {
        try
        {
            // Use a fixed secret key for testing, make sure to securely manage this in a real app
            String keyString = "1234567890123456"; // 16-byte key for AES-128
            secretKey = new javax.crypto.spec.SecretKeySpec(keyString.getBytes(), "AES");
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing encryption key", e);
        }
    }

    // Method to Encrypt Password
    public static String encryptPassword(String password) throws Exception
    {
        // Generate a random IV for each encryption (AES block size is 16 bytes)
        byte[] iv = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Initialize the Cipher for encryption (AES/CBC/PKCS5Padding)
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        // Encrypt the password
        byte[] encryptedBytes = cipher.doFinal(password.getBytes("UTF-8"));

        // Combine the IV and encrypted password into one array
        byte[] ivAndEncryptedPassword = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, ivAndEncryptedPassword, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, ivAndEncryptedPassword, iv.length, encryptedBytes.length);

        // Return the concatenated result as a Base64 encoded string
        return Base64.getEncoder().encodeToString(ivAndEncryptedPassword);
    }

    // Method to Decrypt Password
    public static String decryptPassword(String encryptedPassword) throws Exception
    {
        // Decode the Base64 string to get the combined IV + encrypted password
        byte[] ivAndEncryptedPassword = Base64.getDecoder().decode(encryptedPassword);

        // Extract the IV (first 16 bytes)
        byte[] iv = new byte[16];
        System.arraycopy(ivAndEncryptedPassword, 0, iv, 0, iv.length);

        // Extract the encrypted password (rest of the bytes after IV)
        byte[] encryptedBytes = new byte[ivAndEncryptedPassword.length - iv.length];
        System.arraycopy(ivAndEncryptedPassword, iv.length, encryptedBytes, 0, encryptedBytes.length);

        // Prepare the IvParameterSpec for decryption
        IvParameterSpec ivSpecForDecryption = new IvParameterSpec(iv);

        // Initialize the Cipher for decryption (AES/CBC/PKCS5Padding)
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpecForDecryption);

        // Decrypt the encrypted password bytes
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Return the decrypted password as a string
        return new String(decryptedBytes, "UTF-8");
    }
}
