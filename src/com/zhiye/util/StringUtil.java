package com.zhiye.util;

import java.io.UnsupportedEncodingException;

public class StringUtil {
    public static String iso2UTF(String src) {
        String res = null;
        try {
            res =  new String(src.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("字符集不能转换");
        }
        return res;
    }
}
