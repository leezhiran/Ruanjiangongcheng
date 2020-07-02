package com.example.ruanjiangongcheng.Misc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

public class Base64Tools {
    public static Object decode(String str){
        byte[] s = Base64.getDecoder().decode(str.replace("\r\n", ""));
        Object ret=null;
        ByteArrayInputStream bis = new ByteArrayInputStream(s);
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            ret=ois.readObject();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       return ret;
    }
    /***
     * Haven't been implemented.
     *
     */
    public static String encode(Object obj){
        return new String();
    }
}
