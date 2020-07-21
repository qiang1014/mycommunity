package cn.zhaoq.mycommunity.controller;

import cn.zhaoq.mycommunity.dao.QuestionDao;
import cn.zhaoq.mycommunity.dao.UserDao;
import cn.zhaoq.mycommunity.domain.Question;
import cn.zhaoq.mycommunity.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private QuestionDao questionDao;

    /**
     * 处理get请求
     * @return
     */
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    /**
     * 处理post请求
     * @return
     */
    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request, Model model){

        //验证用户是否登录
        User userToken = null;
        userToken = (User) request.getSession().getAttribute("username");

        if(userToken==null){
            model.addAttribute("error","用户尚未登录");
            return "publish";
        }

        //将数据存入model对象中，便于回写
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        //判断标题是否为空
        if(title == null || title == ""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }

        //判断发布内容是否为空
        if(description == null || description == ""){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }

        //判断标签是否为空
        if(tag == null || tag == ""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        //将数据存入数据库
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(userToken.getId());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());
        questionDao.save(question);
        return "redirect:/";
    }
}
