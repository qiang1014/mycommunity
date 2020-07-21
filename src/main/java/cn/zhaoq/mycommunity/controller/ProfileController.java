package cn.zhaoq.mycommunity.controller;

import cn.zhaoq.mycommunity.dao.QuestionDao;
import cn.zhaoq.mycommunity.dao.UserDao;
import cn.zhaoq.mycommunity.domain.User;
import cn.zhaoq.mycommunity.dto.PageDto;
import cn.zhaoq.mycommunity.dto.QuestionDto;
import cn.zhaoq.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 处理下拉菜单中，我的问题
 */

@Controller
public class ProfileController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/profile/{action}")
    public String profile(@PathVariable(value = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size){

        //验证用户是否登录
        User userToken = null;
        userToken = (User) request.getSession().getAttribute("username");

        if(userToken == null){
            return "redirect:/";
        }

        //如果用户已经登录
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");



        }else if("replie".equals(action)){
            model.addAttribute("section","replie");
            model.addAttribute("sectionName","我的回复");
        }

        List<QuestionDto> myQuestionList = questionService.getMyQuestionList(userToken.getId(), page, size);

        System.out.println(userToken.getId());
        //调用数据库查询当前用户的问题总数
        Integer totalCount = questionService.getMyQuestionTotal(userToken.getId());

        System.out.println(totalCount);

        PageDto pageDto = new PageDto();
        pageDto.setQuestionDtos(myQuestionList);
        pageDto.setPageDto(totalCount,page,size);

        System.out.println(pageDto);

        //将数据存入model对象中
        model.addAttribute("MyQuestionPageDto",pageDto);

        return "profile";
    }
}
