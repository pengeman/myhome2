package org.pengwt.myhome.myhome2.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pengwt.myhome.myhome2.entity.BookMark;
import org.pengwt.myhome.myhome2.service.BookMarkService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
public class BookMarkController {
    @Resource
    public BookMarkService bookMarkService;

    @GetMapping(value = "/allbookmarks")
    public String getAllBookMarks() {
        List<BookMark> bms = bookMarkService.getAllBookMarks();
        String jsonString = JSON.toJSONString(bms);
        //System.out.println(jsonString);
        log.debug(jsonString);
        return jsonString;
    }

    @GetMapping(value = "/myhome")
    public ModelAndView showBookMarks(ModelAndView modelAndView) {
        List<BookMark> bms = bookMarkService.getAllBookMarks();
        String jsonString = JSON.toJSONString(bms);
        //System.out.println(jsonString);
        log.debug(jsonString);
        //写freemarker
        modelAndView.addObject("title", "myhome");
        modelAndView.addObject("rows", bms);
//        for (BookMark bookMark : bms){
//            modelAndView.addObject("rows", bookMark);
//        }
        modelAndView.setViewName("myhome");
        return modelAndView;
    }

    @RequestMapping(value = "/gosetup")
    public ModelAndView gosetup(ModelAndView md) {
        // 检索全部数据，传入setup页面
        List<BookMark> bookMarks = this.bookMarkService.getAllBookMarks();
        md.addObject("title", "setup myhome");
        md.addObject("rows", bookMarks);
        md.setViewName("setupnew");
        return md;
    }

    //  增加新bookmark项
    @PostMapping(value = "/dosetup")
    public String dosetup(@RequestParam(name="name") String name , @RequestParam(name="url") String url){
        BookMark bookMark = new BookMark(name,url);
        boolean r = this.bookMarkService.saveBookMark(bookMark);
        return "新增书签 成功" + r;
    }
    @RequestMapping(value = "/test")
    public ModelAndView HelloTest(ModelAndView m) {
        m.addObject("title", "小明tetetete");
        m.addObject("rows", Lists.newArrayList(
                new BookMark(1, "urlurlurl111", "namnamnam111", null)
                , new BookMark(3, "urlurlurl3333", "namnamnam3333", null)
        ));
        m.setViewName("myhome");
        return m;
    }
}
