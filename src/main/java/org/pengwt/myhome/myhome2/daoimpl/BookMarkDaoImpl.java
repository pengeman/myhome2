package org.pengwt.myhome.myhome2.daoimpl;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import org.pengwt.myhome.myhome2.dao.BookMarkDao;
import org.pengwt.myhome.myhome2.entity.BookMark;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 *
 * @param
 * @author pengweitao 2022/4/11
 * @version v0.1
 * @exception
 * @return
 */
@Component
public class BookMarkDaoImpl implements BookMarkDao {

    private static SqlMapClient sqlMapClient = null;
    private final String home;
    private final String FIlE_URL;
    private final String FILE_file;

    public BookMarkDaoImpl() {
        home = System.getProperty("user.home");
        FIlE_URL = home + "/MyHomePage/data/";
        FILE_file = "myhome";
        try {
            firstone(1); // 检查是否第一次运行
            Reader reader = Resources.getResourceAsReader("mapper/sqlMapConfig.xml");
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
           this.createDB(1);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean saveBookMark(BookMark bookMark) {
        Object object = null;
        boolean flag = false;
        try {
            object = sqlMapClient.insert("saveBookMark", bookMark);
            System.out.println("添加bookmark信息的返回值：" + object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (object != null) {
            flag = true;
        }
        return flag;
    }

    public boolean deleteBookMarkById(long id) {
        boolean flag = false;
        Object object = null;
        try {
            object = sqlMapClient.delete("deleteBookMarkById", id);
            System.out.println("删除BookMark信息的返回值：" + object + "，这里返回的是影响的行数");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (object != null) {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<BookMark> getAllBookMark() {
        List<BookMark> allBookMark = null;
        try {
            allBookMark = sqlMapClient.queryForList("selectAllBookMark");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBookMark;
    }

    @Override
    public BookMark getBookMark(long id) {
        return null;
    }

    @Override
    public boolean saveBookMark(String name, String url) {
        BookMark bookMark = new BookMark(name, url);
        boolean r = saveBookMark(bookMark);
        return r;
    }

    @Override
    public boolean delBookMark(long id) {
        return this.deleteBookMarkById(id);
    }

    @Override
    public boolean updateBookMark(BookMark bookMark) {
        try {
            int r = sqlMapClient.update("updateBookMark");
            return r > 0 ?  true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    第一次运行, 做初始设置, 创建数据库文件目录,创建数据库链接文件及参数
     */
    private void firstone(int ver) {
        // 第一运行时执行，返回1
        java.sql.Connection con = null;
        File file = new File(FIlE_URL);
        File file2 = new File(FIlE_URL + FILE_file);
        System.out.println("mkdirs DB_url");

        try {
            if (!file.exists()) {
                file.mkdirs();
                file2.createNewFile();
                createSqlConfig();
            } else if (!file2.exists()) {
                file2.createNewFile();
                createSqlConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDB(int ver) {

        try {
            int rs = sqlMapClient.update("createTablecc"); //.executeUpdate(sql);

            rs = sqlMapClient.update("createTablever"); //statement.executeUpdate(sql);

            rs = sqlMapClient.update("insertver", 1); //statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    //编辑sqlconfig文件
    private void createSqlConfig() {
        String sqlconfig = "mapper/SqlMap.properties";
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite:" + FIlE_URL + FILE_file;
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

    public static void main(String[] args) {
        BookMarkDaoImpl bkdi = new BookMarkDaoImpl();
//        BookMark bm = new BookMark();
//        bm.setName("aaaaaaaa");
//        bm.setUrl("uuuuuuuuu");
//bkdi.saveBookMark(bm);
    }
}
