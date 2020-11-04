package com.tool;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Objects;


/**
 * Created by Liuxd on 2018-11-02.
 */
public class StringToBase64 {
    public String ToBase64(String str) {
        byte[] bytes = str.getBytes();
        String encoded = Base64.getEncoder().encodeToString(bytes);
        return encoded;
    }

    public static void main(String[] args) {
        StringToBase64 a = new StringToBase64();
        System.out.println(a.ToBase64("aaa"));
    }
}
