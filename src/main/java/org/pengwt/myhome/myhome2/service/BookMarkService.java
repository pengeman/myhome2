package org.pengwt.myhome.myhome2.service;

import org.pengwt.myhome.myhome2.entity.BookMark;
import org.pengwt.myhome.myhome2.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookMarkService {
    public List<BookMark> getAllBookMarks();

    public List<BookMark> getAllBookMarksByUserid(int userid);



    boolean saveBookMark(String name, String url, int userid);

    public boolean saveBookMark(BookMark bookMark, int userid);

    public void uploadBookMark(String bookmark);

    public String download();

    void deleteBookMark(long id);

    boolean loginVerify(String username, String pwd);

    User getUserByName(String username);

    User getUserById(int id);

    void newUser(String username , String pwd);

    void newUser(User user);

    void createTable(String tableName);
    void alterTable(String tableName);
}
