package com.zkxh.demo.common.util.file;

import com.zkxh.demo.common.base.log.BaseLog;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

/**
 * @ClassName FileUtils
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/19 8:58
 * @Vserion v0.0.1
 */

public class FileUtils extends BaseLog {

    //生成文件路径

    //文件路径+名称
    private static String filenameTemp;

    /**
     * @param [ fileName, type]
     * @return boolean
     * @description 创建文件 parameter1 文件的路径 parameter2 文件名称 parameter3 文件类型
     * @date 14:05 2018/9/21
     * @auther lifeng
     **/
    public static boolean createFile(String fileName, FileTypeEnums type) {
        Boolean bool = false;
        filenameTemp = fileName + "." + type;//文件路径+名称+文件类型
        File file = new File(filenameTemp);
        try {
            //如果文件不存在，则创建新的文件
            if (!file.exists()) {
                file.createNewFile();
                bool = true;
                logger.info("创建[" + filenameTemp + "]文件成功");
                //创建文件成功后，写入内容到文件里
                //  writeFileContent(filenameTemp, filecontent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bool;
    }

    /**
     * @param [path, fileName, type]
     * @return boolean
     * @description 创建文件 parameter1 文件的路径 parameter2 文件名称 parameter3 文件类型
     * @date 14:05 2018/9/21
     * @auther lifeng
     **/
    public static boolean createFile(String path, String fileName, FileTypeEnums type) {
        Boolean bool = false;
        filenameTemp = path + File.separator + fileName + "." + type;//文件路径+名称+文件类型
        System.out.println(filenameTemp);
        File file = new File(filenameTemp);
        try {
            //如果文件不存在，则创建新的文件
            if (!file.exists()) {
                file.createNewFile();
                bool = true;
                logger.info("创建[" + filenameTemp + "]文件成功");
                //创建文件成功后，写入内容到文件里
                //  writeFileContent(filenameTemp, filecontent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bool;
    }

    /**
     * 创建文件 并写入文件
     *
     * @param fileName    文件名称
     * @param filecontent 文件内容
     * @return 是否创建成功，成功则返回true
     */
    public static boolean createFile(String path, String fileName, FileTypeEnums type, String filecontent) {
        Boolean bool = false;
        filenameTemp = path + File.separator + fileName + "." + type;//文件路径+名称+文件类型
        File file = new File(filenameTemp);
        try {
            //如果文件不存在，则创建新的文件
            if (!file.exists()) {
                file.createNewFile();
                bool = true;
                logger.info("创建[" + filenameTemp + "]文件成功");
                //创建文件成功后，写入内容到文件里
                writeFileContent(filenameTemp, filecontent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bool;
    }

    /**
     * 向文件中写入内容
     *
     * @param filepath 文件路径与名称
     * @param newstr   写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath, String newstr) throws IOException {
        Boolean bool = false;
        String filein = newstr + "\r\n";//新写入的行，换行
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            //文件原有内容
            for (int i = 0; (temp = br.readLine()) != null; i++) {
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名称
     * @return
     */
    public static boolean delFile(String path, String fileName, FileTypeEnums type) {
        Boolean bool = false;
        filenameTemp = path + File.separator + fileName + "." + type;
        File file = new File(filenameTemp);
        try {
            if (file.exists()) {
                file.delete();
                bool = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return bool;
    }

    public static void main(String[] args) {
        // UUID uuid = UUID.randomUUID();
        String s = String.valueOf(FileTypeEnums.EXE);
        System.out.println(s);
        FileUtils.createFile("aa", FileTypeEnums.MP3);
    }


    /**
     * 获取文件编码格式
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String getFileEncoding(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        return code;
    }


    /**
     * 得到文件的扩展名
     *
     * @param f
     * @return
     */
    public static String getFileExtension(File f) {
        if (f != null) {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if (i > 0 && i < filename.length() - 1) {
                return filename.substring(i + 1).toLowerCase();
            }
        }
        return null;
    }

    /**
     * 得到文件名(排除文件扩展名)
     *
     * @param f
     * @return
     */
    public static String getFileNameWithoutExt(File f) {
        if (f != null) {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if (i > 0 && i < filename.length() - 1) {
                return filename.substring(0, i);
            }
        }
        return null;
    }

    /**
     * 改变文件的扩展名
     *
     * @param fileNM
     * @param ext
     * @return
     */
    public static String changeFileExt(String fileNM, String ext) {
        int i = fileNM.lastIndexOf('.');
        if (i >= 0)
            return (fileNM.substring(0, i) + ext);
        else
            return fileNM;
    }

    /**
     * 得到文件的全路径
     *
     * @param filePath
     * @return
     */
    public static String getFileNameWithFullPath(String filePath) {
        int i = filePath.lastIndexOf('/');
        int j = filePath.lastIndexOf("\\");
        int k;
        if (i >= j) {
            k = i;
        } else {
            k = j;
        }
        int n = filePath.lastIndexOf('.');
        if (n > 0) {
            return filePath.substring(k + 1, n);
        } else {
            return filePath.substring(k + 1);
        }
    }


    /**
     * 判断目录是否存在
     *
     * @param strDir
     * @return
     */
    public static boolean existsDirectory(String strDir) {
        File file = new File(strDir);
        return file.exists() && file.isDirectory();
    }

    /**
     * 判断文件是否存在
     *
     * @param strDir
     * @return
     */
    public static boolean existsFile(String strDir) {
        File file = new File(strDir);
        return file.exists();
    }

    /**
     * 强制创建目录
     *
     * @param strDir
     * @return
     */
    public static boolean forceDirectory(String strDir) {
        File file = new File(strDir);
        file.mkdirs();
        return existsDirectory(strDir);
    }

    /**
     * 得到文件的大小
     *
     * @param fileName
     * @return
     */
    public static int getFileSize(String fileName) {

        File file = new File(fileName);
        FileInputStream fis = null;
        int size = 0;
        try {
            fis = new FileInputStream(file);
            size = fis.available();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

}
