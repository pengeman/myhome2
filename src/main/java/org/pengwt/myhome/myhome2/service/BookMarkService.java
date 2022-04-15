package org.nini.simple.service;

import org.nini.simple.entity.BookMark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookMarkService {
    public List<BookMark> getAllBookMarks();
    public boolean saveBookMark(String name,String url);
    public boolean saveBookMark(BookMark bookMark);


}
