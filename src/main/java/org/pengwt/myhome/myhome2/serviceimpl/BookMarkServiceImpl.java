package org.pengwt.myhome.myhome2.serviceimpl;

import org.pengwt.myhome.myhome2.dao.BookMarkDao;
import org.pengwt.myhome.myhome2.entity.BookMark;
import org.pengwt.myhome.myhome2.entity.User;
import org.pengwt.myhome.myhome2.service.BookMarkService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class BookMarkServiceImpl implements BookMarkService {
    @Resource
    public BookMarkDao bookMarkDao;

    @Override
    public List<BookMark> getAllBookMarks() {
        return bookMarkDao.getAllBookMark();

    }

    @Override
    public boolean saveBookMark(String name, String url) {
        return bookMarkDao.saveBookMark(name, url);
    }

    @Override
    public boolean saveBookMark(BookMark bookMark) {
        String name = bookMark.getName();
        String url = bookMark.getUrl();
        return this.saveBookMark(name, url);
    }

    @Override
    public void uploadBookMark(String bookmark) {

    }

    @Override
    public String download() {
        return null;
    }

    @Override
    public void deleteBookMark(long id) {
        this.bookMarkDao.delBookMark(id);
    }

    @Override
    public boolean loginVerify(String username, String pwd) {
        User user = bookMarkDao.getUserByName(username);
        if (user != null){
            if(user.getName().equals(username) && user.getPwd().equals(pwd))                return true;
            else return false;
        }else {
            return false;
        }
    }

    @Override
    public User getUserByName(String username) {
        return bookMarkDao.getUserByName(username);
            }

    @Override
    public User getUserById(int id) {
        return bookMarkDao.getUserById(id);
    }

}
