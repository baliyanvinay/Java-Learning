package Utilities;

import java.security.SecureRandom;
import java.util.Base64;


public class StringUtil {

    public static String encode(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }

    public static byte[] decodeInBytes(String input) {
        return Base64.getDecoder().decode(input);
    }

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[16];
        random.nextBytes(randomBytes);
        return randomBytes;
    }

}
