package org.nini.simple.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.nini.simple.entity.BookMark;
import org.nini.simple.service.BookMarkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Log4j2
@RestController
public class BookMarkController {
    @Resource
    public BookMarkService bookMarkService;
    @GetMapping(value="/allbookmarks")
    public String getAllBookMarks(){
        List<BookMark> bms = bookMarkService.getAllBookMarks();
        String jsonString = JSON.toJSONString(bms);
        //System.out.println(jsonString);
        log.debug(jsonString);
        return jsonString;
    }

    public ModelAndView showBookMarks(){
        List<BookMark> bms = bookMarkService.getAllBookMarks();
        String jsonString = JSON.toJSONString(bms);
        //System.out.println(jsonString);
        log.debug(jsonString);
        return new ModelAndView("showBookMarks");
    }
}
