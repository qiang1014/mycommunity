package cn.zhaoq.mycommunity.service;

import cn.zhaoq.mycommunity.dao.QuestionDao;
import cn.zhaoq.mycommunity.dao.UserDao;
import cn.zhaoq.mycommunity.domain.Question;
import cn.zhaoq.mycommunity.domain.User;
import cn.zhaoq.mycommunity.dto.QuestionDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 调用dao接口
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private UserDao userDao;

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public List<QuestionDto> getQuestionList(Integer page, Integer size){

        Integer offset = (page-1)*size;

        List<QuestionDto> questionDtos = new ArrayList<>();
        //调用QuestionDao获取所有的Question对象
        List<Question> questionDaoAll = questionDao.findByPage(offset,size);

        for (Question question : questionDaoAll) {
            //使用UserDao获取User对象
            User user = userDao.getOne(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);

            questionDtos.add(questionDto);
        }

        return questionDtos;
    }

    public Integer getTotalQuestion(){

        return questionDao.getTotal();
    }

    public List<QuestionDto> getMyQuestionList(Integer id, Integer page, Integer size) {
        Integer offset = (page-1)*size;

        List<QuestionDto> questionDtos = new ArrayList<>();
        //调用QuestionDao获取所有的Question对象
        List<Question> questionDaoAll = questionDao.getMyQuestions(id,offset,size);

        for (Question question : questionDaoAll) {
            //使用UserDao获取User对象
            User user = userDao.getOne(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);

            questionDtos.add(questionDto);
        }

        return questionDtos;
    }

    /**
     * 获取我的问题总数
     * @return
     */
    public Integer getMyQuestionTotal(Integer id) {
        return questionDao.getMyQuestionCount(id);
    }
}

