package com.projecthellfire.core.util;

import com.projecthellfire.core.exception.PasswordEncryptionException;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class PasswordUtil {
    public static String encrypt(String decryptedPassword) throws PasswordEncryptionException {
        try {
            MessageDigest message = MessageDigest.getInstance("MD5");
            message.update(decryptedPassword.getBytes());

            byte[] bytes = message.digest();

            StringBuilder builder = new StringBuilder();
            for (byte aByte : bytes) {
                builder.append(Integer.toString((aByte & 0xff) + 0x100, 16)
                        .substring(1));
            }

            return builder.toString();

        } catch (NoSuchAlgorithmException e) {
            log.error("Could not encrypt password.");
            throw new PasswordEncryptionException(e.getMessage());
        }
    }
}
