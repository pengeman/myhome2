package org.pengwt.myhome.myhome2.controller;
//202403171700
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.log4j.Log4j2;
import org.pengwt.myhome.myhome2.entity.BookMark;
import org.pengwt.myhome.myhome2.entity.User;
import org.pengwt.myhome.myhome2.service.BookMarkService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    public ModelAndView showBookMarks(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();

        String footer = "";

        // todo 判断是否登录
        String user = (String) session.getAttribute("username");
        if (user == null) {
            log.info("用户没有登陆");
            footer = "<a href=\"gologin\" target=\"_self\">[login]</a>";
        } else {
            log.info("用户登陆...");
            List<BookMark> bms = bookMarkService.getAllBookMarks();
            modelAndView.addObject("rows", bms);
            footer = "<a href=\"gosetup\">[setup]</a>&nbsp&nbsp<a href=\"gosync\">[同步]</a>";
        }
        // todo 写freemarker
        modelAndView.addObject("title", "myhome");

        modelAndView.addObject("footer_var", footer);
        modelAndView.setViewName("myhome");
        return modelAndView;
    }


    @RequestMapping(value = "/gosetup")
    public ModelAndView gosetup(HttpServletRequest request) {
        // 检索全部数据，传入setup页面
        ModelAndView md = new ModelAndView();
        int userid = (Integer) request.getSession().getAttribute("userid");
        List<BookMark> bookMarks = this.bookMarkService.getAllBookMarks();
        this.bookMarkService.getAllBookMarksByUserid(userid);
        md.addObject("title", "setup myhome");
        md.addObject("rows", bookMarks);
        md.setViewName("setupnew");
//        md.setViewName("setupnew2");
        return md;
    }

    //  增加新bookmark项
    @PostMapping(value = "/dosetup")
    public String dosetup(HttpServletRequest request) {
        //@RequestParam(name="name") String name , @RequestParam(name="url") String url
        String name = request.getParameter("name");
        String url = request.getParameter("user");
        String username = (String) request.getSession().getAttribute("user");
        User user = bookMarkService.getUserByName(username);
        BookMark bookMark = new BookMark(name, url, user.getId());
        boolean r = this.bookMarkService.saveBookMark(bookMark);
        String s = "新增书签 成功" + r;
        return JSON.toJSONString(s);
    }

    // 删除一条记录
    @PostMapping(value = "/setupdeleteitem")
    public ModelAndView setupdeleteitem(@RequestParam(name = "id") long id) {
        this.bookMarkService.deleteBookMark(id);
        return new ModelAndView("gosetup");
    }


    @RequestMapping(value = "/gosync")
    public ModelAndView gosync(ModelAndView md) {
        // 进入同步页面，点击开始同步，将服务端数据下载，更新本地数据，再将本地新数据上传服务端
        md.setViewName("sync");
        return md;
    }

    @GetMapping(value = "/gologin")
    public ModelAndView gologin(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        modelAndView.addObject("error", "");
        return modelAndView;
    }

    @PostMapping(value = "/dologin")
    public ModelAndView dologin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        boolean ok = bookMarkService.loginVerify(username, pwd);
        ModelAndView modelAndView = new ModelAndView();

        if (ok) {
            User user = bookMarkService.getUserByName(username);
            int userid = user.getId();
            session.setAttribute("username", username);
            session.setAttribute("userid", userid);
//            modelAndView.addObject("title","myhome");


            modelAndView.setViewName("gohome");
//            List<BookMark> bms = bookMarkService.getAllBookMarks();
//            modelAndView.addObject("rows", bms);
        } else {
            session.removeAttribute("user");
            modelAndView.addObject("title", "login");
            modelAndView.addObject("error", "用户名及密码错误");
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @PostMapping(value = "/doSignup")
    public ModelAndView doSignup(HttpServletRequest request) {
        ModelAndView md = new ModelAndView("login");
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        String repwd = request.getParameter("password2");
        if (username == null || username.isEmpty()) {
            md.addObject("error", "用户名不能空");
            md.setViewName("login");
            return md;
        }
        if (!pwd.equals(repwd)) {
            md.addObject("error", "用户名口令不一致");
            md.setViewName("login");
            return md;
        } else {
            // todo 注册信息正确，允许注册
            bookMarkService.newUser(username, pwd);
            md.addObject("error", "注册成功，再次登录");
            md.setViewName("login");
            return md;
        }
    }
}
