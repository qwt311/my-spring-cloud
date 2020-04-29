package cloud.service.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 通过Java的Zip输入输出流实现压缩和解压文件
 *
 * @author wentao.qiao
 *
 */
public final class ZipUtil {

    /**
     * 压缩文件列表到某个zip包
     * @param zipFileName zip包文件名
     * @param paths 文件列表路径
     * @throws IOException
     */
    public static void compress(String zipFileName,String[] paths) throws IOException {
        compress(new FileOutputStream(zipFileName),paths);
    }


    /**
     * 压缩文件列表到某个zip包
     * @param stream 流
     * @param paths 文件列表路径
     * @throws IOException
     */
    public static void compress(OutputStream stream, String[] paths) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(stream);
        for (String path : paths){
            if (StringUtils.equals(path,"")){
                continue;
            }
            File file = new File(path);
            if (file.exists()){
                if (file.isDirectory()){
                    zipDirectory(zos,file.getPath(),file.getName() + File.separator);
                } else {
                    zipFile(zos,file.getPath(),"");
                }
            }
        }
        zos.close();
    }


    /**
     * 解析多文件夹
     * @param zos zip流
     * @param dirName 目录名称
     * @param basePath
     * @throws IOException
     */
    private static void zipDirectory(ZipOutputStream zos,String dirName,String basePath) throws IOException {
        File dir = new File(dirName);
        if (dir.exists()){
            File files[] = dir.listFiles();
            if (files.length > 0){
                for (File file : files){
                    if (file.isDirectory()){
                        zipDirectory(zos,file.getPath(),file.getName() + File.separator);
                    } else {
                        zipFile(zos,file.getName(),basePath);
                    }
                }
            } else {
                ZipEntry zipEntry = new ZipEntry(basePath);
                zos.putNextEntry(zipEntry);
            }
        }
    }


    private static void zipFile(ZipOutputStream zos,String fileName,String basePath) throws IOException {
        File file = new File(fileName);
        if (file.exists()){
            FileInputStream fis = new FileInputStream(fileName);
            ZipEntry ze = new ZipEntry(basePath + file.getName());
            zos.putNextEntry(ze);
            byte[] buffer = new byte[8192];
            int count = 0;
            while ((count = fis.read(buffer)) > 0){
                zos.write(buffer,0,count);
            }
            fis.close();
        }
    }
}
