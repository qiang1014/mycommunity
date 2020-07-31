package cn.zhaoq.mycommunity.controller;


import cn.zhaoq.mycommunity.dao.QuestionDao;
import cn.zhaoq.mycommunity.dto.QuestionDto;
import cn.zhaoq.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("question/{id}")
    public String question(@PathVariable(name="id") Integer id,
                           Model model){
        //根据问题的id查询当前问题的详细信息
        QuestionDto questionDto = questionService.getDetailQuestion(id);

        model.addAttribute("question",questionDto);
        return "question";
    }

}
