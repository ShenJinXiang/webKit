package com.qysoft.rapid.utils;

import com.jfinal.kit.StrKit;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenjinxiang on 2017/9/10.
 */
public class PackageUtil {

    public static List<String> scanPackage(String pkg) {
        if (StrKit.isBlank(pkg)) {
            return null;
        }
        try {
            String pkgName = pkg.replace(".", File.separator);
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            URL url = loader.getResource(pkgName);
            String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
            List<String> list = getClassNames(filePath, pkgName, null);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> getClassNames(String filePath, String rootPath, List<String> className) {
        List<String> list = new ArrayList<>();
        File file = new File(filePath);
        File[] children = file.listFiles();
        for (File child : children) {
            if (child.isDirectory()) {
                list.addAll(getClassNames(child.getAbsolutePath(), rootPath, list));
            } else {
                String childFilePath = child.getAbsolutePath();
                if (null != childFilePath && childFilePath.endsWith(".class")) {
                    String name = childFilePath.substring(childFilePath.indexOf(rootPath), childFilePath.lastIndexOf(".class"));
                    list.add(name.replace(File.separator, "."));
                }
            }
        }
        return list;
    }


    public static void main(String[] args) {
        String pkg = "com.qysoft";
        List<String> list = PackageUtil.scanPackage(pkg);
        for(String c : list ) {
            System.out.println(c);
        }
    }
}
