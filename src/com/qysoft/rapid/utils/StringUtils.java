/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qysoft.rapid.utils;

import com.jfinal.kit.StrKit;
import com.qysoft.rapid.consts.RapidConsts;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 *
 * @author huangwei
 */
public class StringUtils {

    public static String getMD5_SALT(String password) {
        return stringToMD5(password,RapidConsts.MD5_SALT);
    }
    private static String stringToMD5(String str,Object salt){
        return new Md5Hash(str, salt).toString();
    }

    public static String toLowerCaseFirstChar(String str) {
        if (StrKit.isBlank(str)) {
            return "";
        }
        if (Character.isLowerCase(str.charAt(0))) {
            return str;
        }
        String result = new StringBuilder().append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
        return result;
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.toLowerCaseFirstChar("abcde"));
        System.out.println(StringUtils.toLowerCaseFirstChar("Abcde"));
        System.out.println(StringUtils.toLowerCaseFirstChar("ABcde"));
        System.out.println(StringUtils.toLowerCaseFirstChar("a"));
        System.out.println(StringUtils.toLowerCaseFirstChar(""));
        System.out.println(StringUtils.toLowerCaseFirstChar(null));
    }
}
