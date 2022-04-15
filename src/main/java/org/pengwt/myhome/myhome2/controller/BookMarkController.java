package org.pengwt.myhome.myhome2.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.pengwt.myhome.myhome2.entity.BookMark;
import org.pengwt.myhome.myhome2.service.BookMarkService;
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
