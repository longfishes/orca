package com.longfish.orca;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESEncrypter {

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "jd0lckw294hjg2av";

    public static String encrypt(String data) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            String originalData = "Hello World!";
            String encryptedData = encrypt(originalData);
            String decryptedData = decrypt(encryptedData);

            System.out.println("加密前数据: " + originalData);
            System.out.println("加密后数据: " + encryptedData);
            System.out.println("解密后数据: " + decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
