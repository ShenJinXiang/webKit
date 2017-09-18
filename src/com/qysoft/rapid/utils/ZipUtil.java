package com.qysoft.rapid.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by shenjinxiang on 2017-07-29.
 */
public class ZipUtil {

    public static File zip(String zipFileName, String sourceFileName) {
        ZipOutputStream out = null;
        BufferedOutputStream bos = null;
        try {
            out = new ZipOutputStream( new FileOutputStream(zipFileName));
            bos = new BufferedOutputStream(out);
            File sourceFile = new File(sourceFileName);

            compress(out, bos, sourceFile, sourceFile.getName());
            return new File(zipFileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }


    private static void compress(ZipOutputStream out, BufferedOutputStream bos, File sourceFile, String base) throws Exception {
        //如果路径为目录（文件夹）
        if(sourceFile.isDirectory()) {

            //取出文件夹中的文件（或子文件夹）
            File[] flist = sourceFile.listFiles();

            if(flist.length==0) { //如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点

                out.putNextEntry(  new ZipEntry(base+"/") );
            } else { //如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩

                for(int i=0;i<flist.length;i++) {
                    compress(out,bos,flist[i],base+"/"+flist[i].getName());
                }
            }
        } else { //如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
            out.putNextEntry( new ZipEntry(base) );
            FileInputStream fos = new FileInputStream(sourceFile);
            BufferedInputStream bis = new BufferedInputStream(fos);

            int tag;
            //将源文件写入到zip文件中
            while((tag=bis.read())!=-1) {
                bos.write(tag);
            }
            bos.flush();
            bis.close();
            fos.close();
        }
    }

    public static void main(String[] args) {
        String zipFileName = "D:\\work\\Z\\khjl_qrcode.zip";
        String sourceFileName = "D:\\work\\Z\\khjl_qrcode";
        File file = ZipUtil.zip(zipFileName, sourceFileName);
        System.out.println(file.getAbsolutePath());
    }
}
