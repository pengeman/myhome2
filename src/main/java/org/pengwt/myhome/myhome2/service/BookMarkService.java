package org.pengwt.myhome.myhome2.service;

import org.pengwt.myhome.myhome2.entity.BookMark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookMarkService {
    public List<BookMark> getAllBookMarks();
    public boolean saveBookMark(String name,String url);
    public boolean saveBookMark(BookMark bookMark);


}
