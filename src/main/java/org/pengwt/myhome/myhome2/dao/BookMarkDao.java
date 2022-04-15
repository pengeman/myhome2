package org.pengwt.myhome.myhome2.dao;

import org.apache.ibatis.annotations.Mapper;
import org.pengwt.myhome.myhome2.entity.BookMark;
import java.util.List;

/**
 * @param
 * @exception
 * @return
 * @version 1.0
 * @author pengweitao 2022/4/11
 */

@Mapper
public interface BookMarkDao {
    // 读取书签信息
    public List<BookMark> getAllBookMark();
    public BookMark getBookMark(long id);

    // 写入书签信息
    public boolean saveBookMark(String name,String url);
    public boolean delBookMark(long id);
    public boolean updateBookMark(BookMark bookMark);
}
