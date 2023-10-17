package org.pengwt.myhome.myhome2.daoimpl;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import lombok.val;
import org.pengwt.myhome.myhome2.NewMan;
import org.pengwt.myhome.myhome2.dao.BookMarkDao;
import org.pengwt.myhome.myhome2.entity.BookMark;
import org.pengwt.myhome.myhome2.entity.User;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @param
 * @author pengweitao 2022/4/11
 * @version v0.1
 * @exception
 * @return
 */
@Component
public class BookMarkDaoImpl implements BookMarkDao {

    private static SqlMapClient sqlMapClient = null;


    /**
     *
     */
    public SqlMapClient getSqlMapClient() {
        // todo : getSqlMapClient
        try {
            val properties = new Properties();
            val sqlMapConfigUrl = new File(NewMan.getSqlConfigPath()).toURI().toString();
            properties.put("SqlMapConfig", sqlMapConfigUrl);
            Reader reader = Resources.getResourceAsReader("mapper/sqlMapConfig.xml");
            return SqlMapClientBuilder.buildSqlMapClient(reader, properties);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }

    public BookMarkDaoImpl() {
        sqlMapClient = getSqlMapClient();
    }

    public boolean saveBookMark(BookMark bookMark) {
        Object object = null;
        boolean flag = false;
        try {
            object = sqlMapClient.insert("saveBookMark", bookMark);
            //System.out.println("添加bookmark信息的返回值：" + object);
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
        int id2 = (int) id;
        try {
            object = sqlMapClient.delete("deleteBookMarkById", id2);
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
    public List<BookMark> getAllBookMarkByUserid(int userid) {
        List<BookMark> allBookMark = null;

        try {
            allBookMark = sqlMapClient.queryForList("selectBookMarkByUser",userid);
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
        return false;
    }

    @Override
    public boolean saveBookMark(String name, String url, int userid) {
        BookMark bookMark = new BookMark(name, url, userid);
        boolean r = saveBookMark(bookMark);
        return r;
    }

    @Override
    public boolean delBookMark(long id) {
        return this.deleteBookMarkById(id);
    }

    @Override
    public boolean updateBookMark(BookMark bookMark) {
        System.out.println("updateBookMark have not finish");
        try {
            int r = sqlMapClient.update("updateBookMark");
            return r > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void createTable(String tablename) {
        try {
            sqlMapClient.update("createTablecc");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void alterTable(String tablename) {
        try {
            sqlMapClient.update("createTableUser");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(long id) {
        User user = null;
        try {
            user = (User) sqlMapClient.queryForObject(id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 得到用户，根据名称
     *
     * @param username
     * @return
     */
    @Override
    public User getUserByName(String username) {
        User user = null;
        try {
            user = (User) sqlMapClient.queryForObject("getUserByName", username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean insertUser(User user) {
        try {
            Object o = sqlMapClient.insert("insertuser", user);
            if (o != null) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertUser(String name, String pwd) {
        User user = new User<>(name, pwd);
        return this.insertUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        System.out.println("没有完成");
        return false;
    }

    @Override
    public boolean UpdateUser(String name, String pwd) {
        System.out.println("没有完成");
        return false;
    }


    public static void main(String[] args) {
        BookMarkDaoImpl bkdi = new BookMarkDaoImpl();
    }
}
