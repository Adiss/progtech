/**
 * Created by Jakab on 2015.03.11..
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utility {

    public static String byteArrayToHex(byte[] array){
        StringBuilder builder = new StringBuilder(array.length * 2);
        for(byte b : array)
            builder.append(String.format("%02x", b));
        return builder.toString();
    }

    public static String SHA1(String user, String pw){
        MessageDigest md;
        byte[] sha1hash = new byte[40];
        String toEncrypt = user+pw;
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(toEncrypt.getBytes("utf-8"), 0, toEncrypt.length());
            sha1hash = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return byteArrayToHex(sha1hash);
    }

}
