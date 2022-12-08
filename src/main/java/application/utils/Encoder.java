package application.utils;

import java.util.Base64;

/**
 * The class <b>Encoder</b> is used to encrypt different data.
 */
public class Encoder {
    private Encoder() {}

    /**
     * @param string the String to encode
     * @return the encoded String
     */
    public static String encode(String string) {
        return Base64.getEncoder().encodeToString(string.getBytes());
    }

    /**
     * @param string the String to decode
     * @return the decoded String
     */
    public static String decode(String string) {
        return new String(Base64.getDecoder().decode(string));
    }
}
