package app.org.halsa360.Util;

/**
 * Created by vaheed on 28/1/16.
 */


import java.security.MessageDigest;

import Decoder.BASE64Encoder;

public class Encrypt {

    public synchronized String encrypt(String text) throws Exception {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            throw new Exception("Exception in getInstance of Encrypt.encrypt" + e.getMessage());
        }
        try {
            md.update(text.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new Exception("Exception in getBytes of Encrypt.encrypt" + e.getMessage());
        }
        byte raw[] = md.digest();
        String hash = (new BASE64Encoder()).encode(raw);
        return hash;
    }
    public static void main(String[] args) throws Exception{
        System.out.println(new Encrypt().encrypt("vidhya"));

    }
}

