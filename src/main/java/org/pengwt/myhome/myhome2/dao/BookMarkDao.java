package org.pengwt.myhome.myhome2.dao;

import org.apache.ibatis.annotations.Mapper;
import org.pengwt.myhome.myhome2.entity.BookMark;
import org.pengwt.myhome.myhome2.entity.User;

import java.util.List;

/**
 * @param
 * @author pengweitao 2022/4/11
 * @version 1.0
 * @exception
 * @return
 */

@Mapper
public interface BookMarkDao {
    // 读取书签信息
    public List<BookMark> getAllBookMark();

    public List<BookMark> getAllBookMarkByUserid(int userid);

    public BookMark getBookMark(long id);

    boolean saveBookMark(String name, String url);

    // 写入书签信息
    public boolean saveBookMark(String name, String url, int userid);

    public boolean delBookMark(long id);

    public boolean updateBookMark(BookMark bookMark);

    public void createTable(String tablename);

    public void alterTable(String tablename);

    public User getUserById(long id);

    public User getUserByName(String username);

    public boolean insertUser(User user);
    public boolean insertUser(String name, String pwd);

    public boolean updateUser(User user);
    public boolean UpdateUser(String name,String pwd);
}
