package org.pengwt.myhome.myhome2;

import com.ibatis.common.resources.Resources;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.juli.logging.Log;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @Description 一个门岗,判断是否第一次运行app,以便建立数据库配置,判断家目录下/MyHomePage/data/目录是否存在
 * 判断数据库文件myhome是否存在,编辑mapper/SqlMap.properties数据库配置
 * @Author pengweitao
 * @param
 * @exception
 * @return
 * @version 1.0
 */

public class NewMan {
    private String FIlE_URL =System.getProperty("user.home") + "/MyHomePage/data/";
    private String FILE_DB = "myhome"; // 数据库文件

    /*
    第一次运行, 做初始设置, 创建数据库文件目录,创建数据库链接文件及参数
     */
    public void firstone(int ver) {
        // 第一运行时执行，返回1
        File file_url = new File(FIlE_URL);
        File file_db = new File(FIlE_URL + FILE_DB);

        try {
            if (!file_url.exists()) {
                file_url.mkdirs();
                file_db.createNewFile();
                createSqlConfig();
            } else if (!file_db.exists()) {
                file_db.createNewFile();
                createSqlConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDB(int ver) {
        // 讲db文件写入制定的目录
        FileInputStream is = null;
        FileOutputStream os = null;
        try {
            File srfile = Resources.getResourceAsFile("/mapper/" + FILE_DB);
            File descFile = Resources.getResourceAsFile(FIlE_URL + FILE_DB);
            is = new FileInputStream(srfile);
            os = new FileOutputStream(descFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //编辑sqlconfig文件
    private void createSqlConfig() {
        String sqlconfig = "mapper/SqlMap.properties";
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite:" + FIlE_URL + FILE_DB ;
        String username = "****";
        String password = "****";

        try {
            Properties pps = Resources.getResourceAsProperties(sqlconfig);
            File file_sqlconfig = Resources.getResourceAsFile(sqlconfig);

            //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
            //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream out = new FileOutputStream(file_sqlconfig);
            pps.setProperty("driver", driver);
            pps.setProperty("url",url);
            pps.setProperty("username",username);
            pps.setProperty("password",password);

            //以适合使用 load 方法加载到 Properties 表中的格式，
            //将此 Properties 表中的属性列表（键和元素对）写入输出流
            pps.store(out, new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date()) + "create this page");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //写入Properties信息

    public static void WriteProperties(String filePath, String pKey, String pValue) throws IOException {


    }

}
