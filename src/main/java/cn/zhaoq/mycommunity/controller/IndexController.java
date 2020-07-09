package cn.zhaoq.mycommunity.controller;

import cn.zhaoq.mycommunity.dao.UserDao;
import cn.zhaoq.mycommunity.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/")
    public String hello(HttpServletRequest request){
        //先获取名称为token的cookie，若存在则使用数据库查询cookie的值进行验证
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    //连接数据库
                    User userToken = userDao.findByToken(cookie.getValue());
                    if(userToken!=null){
                        //将数据存入session中
                        request.getSession().setAttribute("username",userToken.getName());
                    }
                    break;
                }
            }
        }


        return "index";
    }
}
