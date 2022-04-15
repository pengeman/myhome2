package org.nini.simple.serviceimpl;

import org.nini.simple.dao.BookMarkDao;
import org.nini.simple.entity.BookMark;
import org.nini.simple.service.BookMarkService;
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
