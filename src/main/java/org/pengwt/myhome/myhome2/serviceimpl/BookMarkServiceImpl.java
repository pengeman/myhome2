package org.pengwt.myhome.myhome2.serviceimpl;

import org.pengwt.myhome.myhome2.dao.BookMarkDao;
import org.pengwt.myhome.myhome2.entity.BookMark;
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
        return false;
    }

    @Override
    public boolean saveBookMark(BookMark bookMark) {
        return false;
    }

}
