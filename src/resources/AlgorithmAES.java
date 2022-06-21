package resources;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Ephraim
 */
public class AlgorithmAES {

    private String message;

    public AlgorithmAES(String message) {
        this.message = message;
    }

    //Secret key generation using KeyGenerator class
    private SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        //n can be 128, 192 or 256
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    //Secret key generation using SecretKeyFactory class with the PBKDF2WithHmacSHA256 algorithm
    public SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        return secret;
    }

    //encryption function
    public String[] encryptMessage() {
        try {
            String algorithm = "AES";
            SecretKey key = generateKey(128);

            Cipher cipher = Cipher.getInstance(algorithm);
            //cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cipherText = cipher.doFinal(this.message.getBytes());

            String[] result = {
                Base64.getEncoder().encodeToString(cipherText),
                Base64.getEncoder().encodeToString(key.getEncoded())
            };

            return result;
        } catch (InvalidKeyException
                | NoSuchAlgorithmException | BadPaddingException
                | IllegalBlockSizeException | NoSuchPaddingException ex) {

            System.out.print(ex.getMessage());
        }
        return null;
    }

    //Decryption function
    public String decryptMessage(String raw_key)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        byte[] decodedKey = Base64.getDecoder().decode(raw_key);
        SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        String algorithm = "AES";
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(this.message));

        return new String(plainText);
    }

}
