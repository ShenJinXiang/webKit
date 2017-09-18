package com.qysoft.rapid.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * Created by shenjinxiang on 2017-04-19.
 */
public class DESUtil {
    private final static String DES = "DES";
    private final static String ENCODE = "UTF-8";
    private final static String defaultKey = "[#^$c!s`";

    /**
     * 使用 默认key 加密
     */
    public static String encrypt(String data) {
        return encrypt(data, defaultKey);
    }

    /**
     * 使用 默认key 解密
     *
     */
    public static String decrypt(String data) {
        return decrypt(data, defaultKey);
    }

    /**
     * 根据键值进行加密
     */
    public static String encrypt(String data, String key) {
        try {
            byte[] bt = encrypt(data.getBytes(ENCODE), key.getBytes(ENCODE));
            String strs = new BASE64Encoder().encode(bt);
            return strs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据键值进行解密
     */
    public static String decrypt(String data, String key) {
        try {
            if (data == null)
                return null;
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] buf = decoder.decodeBuffer(data);
            byte[] bt = decrypt(buf, key.getBytes(ENCODE));
            return new String(bt, ENCODE);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据键值进行加密
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * 根据键值进行解密
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
//        String data = "{\"createTime\":\"2017-04-19 18:06:41\",\"endTime\":\"2017-04-19 18:11:41\",\"jqbh\":\"89014103211118510720\",\"token\":\"f97e7cc663354c21b946bcfb279b73fc\"}";
//
//        String str3 = encrypt(data);
//        System.out.println(str3);
//        String str4 = decrypt(str3);
//        System.out.println(str4);
//        String key = "12345670";
//        String str3 = encrypt(data, key);
//        System.out.println(str3);
//        String str4 = decrypt(str3, key);
//        System.out.println(str4);
        String str = "{\"jqbh\": \"a123456\", \"token\": \"88bbbb309b2f4ea499b2087db3c610a6\", \"path\": \"/Data/Uploads/image/2016-03-21/pic/2015111536.png\"}";
        System.out.println(DESUtil.encrypt("{\"jqbh\":\"DD16E0E1-0875-4FC9-8C3D-23A5447E6D01\"}"));
        System.out.println(DESUtil.encrypt("{\"jqbh\":\"DD16E0E1-0875-4FC9-8C3D-23A5447E6D01\"}").equals("5DjXzpB8qlOpLl0wA4Qv7mz1eDoekRz00oD55irsvptvwRlpQPiNZR0iI1fj0GnK") );
        System.out.println(DESUtil.decrypt("5DjXzpB8qlOpLl0wA4Qv7mz1eDoekRz00oD55irsvptAphxZsEVlcvabfs8QzCqUlojKTiB/Qtg="));
    }
}
