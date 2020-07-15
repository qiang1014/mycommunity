package cn.zhaoq.mycommunity.controller;

import cn.zhaoq.mycommunity.dao.UserDao;
import cn.zhaoq.mycommunity.domain.User;
import cn.zhaoq.mycommunity.dto.PageDto;
import cn.zhaoq.mycommunity.dto.QuestionDto;
import cn.zhaoq.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request,Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){
        //先获取名称为token的cookie，若存在则使用数据库查询cookie的值进行验证
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length!=0){
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

        //获取列表分页数据
        List<QuestionDto> questionList = questionService.getQuestionList(page,size);

        //调用数据库查询问题总数
        Integer totalCount = questionService.getTotalQuestion();

        PageDto pageDto = new PageDto();
        pageDto.setQuestionDtos(questionList);
        pageDto.setPageDto(totalCount,page,size);

        //将数据存入model对象中
        model.addAttribute("pageDto",pageDto);
        return "index";
    }
}
