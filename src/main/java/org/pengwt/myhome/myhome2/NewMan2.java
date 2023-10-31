package org.pengwt.myhome.myhome2;

import com.ibatis.common.resources.Resources;
import lombok.extern.log4j.Log4j2;
import lombok.val;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @param
 * @version 1.0
 * @Description 一个门岗, 判断是否第一次运行app, 以便建立数据库配置, 判断家目录下/MyHomePage/data/目录是否存在
 * 判断数据库文件myhome是否存在,编辑mapper/SqlMap.properties数据库配置
 * @Author pengweitao
 * @exception
 * @return
 */
@Log4j2
public class NewMan2 {
    final private static String DATA_DIR = System.getProperty("user.home") + "/MyHomePage/data/";
    final private static String FILE_DB = "myhome.db"; // 数据库文件
    final private static String SQL_CONFIG_PATH = DATA_DIR + "SqlMap.properties";

    static public String getSqlConfigPath() {
        return SQL_CONFIG_PATH;
    }

    static public String getDBFilePath() {
        return DATA_DIR + FILE_DB;
    }

    /*
    每次运行都执行这段程序，检查当前版本是否需要更新，是否需要更新数据库和配置文件
     */
    public void firstone(int ver) {
        File file_url = new File(DATA_DIR);
        File file_db = new File(DATA_DIR + FILE_DB);
        log.info("file_url: {}", file_url);
        log.info("file_db: {}", file_db);
        try {
            if (!file_url.exists()) {
                file_url.mkdirs();
                log.info("mkdirs {}", file_url);
                //file_db.createNewFile();
                createDB(ver);
                createSqlConfig(ver);
            }else {
                int oldver = checkver(ver);
                if (ver > oldver){
                    // 如果有新版本，执行相应更新操作
                    updateVer(ver);
                    createSqlConfig(ver);
                }
            }
        } catch (Exception e) {
            log.error("firstone error", e);
            throw new RuntimeException(e);
        }
    }

    private void updateVer(int ver) {
        // 执行新的更新操作


    }

    private int checkver(int ver) {
        String templateSqlconfig = "mapper/SqlMap.properties";
        try {
            Properties pps = Resources.getResourceAsProperties(templateSqlconfig);
            String v = pps.getProperty("ver");
            if (v == null ) v = "0";
            return Integer.parseInt(v);
        } catch (IOException e) {
            System.out.println(e);
            return 0;
        }
    }

    void createDB(int ver) throws IOException {
        // 讲db文件写入制定的目录
        // 因为无法通过 new File 来访问jar中的文件, 所以使用流
        val srcfile = Resources.getResourceAsStream("mapper/" + FILE_DB);
        File descFile = new File(DATA_DIR + FILE_DB);
        Files.copy(srcfile, descFile.toPath());
        log.info("createDB {} -> {}", srcfile, descFile);
    }

    //编辑sqlconfig文件
    private void createSqlConfig(int ver) throws IOException {
        // 模板
        String templateSqlconfig = "mapper/SqlMap.properties";
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite:" + DATA_DIR + FILE_DB;
        String username = "****";
        String password = "****";

        Properties pps = Resources.getResourceAsProperties(templateSqlconfig);
        File file_sqlconfig = new File(SQL_CONFIG_PATH);

        //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
        //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
        OutputStream out = Files.newOutputStream(file_sqlconfig.toPath());
        pps.setProperty("driver", driver);
        pps.setProperty("url", url);
        pps.setProperty("username", username);
        pps.setProperty("password", password);
        pps.setProperty("ver",ver+"");

        //以适合使用 load 方法加载到 Properties 表中的格式，
        //将此 Properties 表中的属性列表（键和元素对）写入输出流
        pps.store(out, new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date()) + "create this page");
        out.close();
        log.info("createSqlConfig {}", file_sqlconfig.getPath());
    }

}