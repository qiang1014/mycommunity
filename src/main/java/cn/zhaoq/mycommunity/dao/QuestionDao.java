package cn.zhaoq.mycommunity.dao;

import cn.zhaoq.mycommunity.domain.Question;
import cn.zhaoq.mycommunity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question,Integer>, JpaSpecificationExecutor<Question> {


    /**
     * 分页查询  使用sql语句进行查询
     * @param offset
     * @param size
     * @return
     */
    @Query(value="select *from question limit ?,?",nativeQuery=true)
    public List<Question> findByPage(Integer offset, Integer size);

    /**
     * 查询总记录数
     * @return
     */
    @Query(value="select count(1) from question",nativeQuery=true)
    public Integer getTotal();

    /**
     * 根据用户ID查询当前用户的问题，分页展示
     * @param id
     * @param page
     * @param size
     * @return
     */
    @Query(value="select *from question where creator = ? limit ?,?",nativeQuery=true)
    public List<Question> getMyQuestions(Integer id, Integer page, Integer size);

    /**
     * 根据用户id查询当前用户的总问题数
     * @param id
     * @return
     */
    @Query(value="select count(1) from question where creator = ?",nativeQuery=true)
    public Integer getMyQuestionCount(Integer id);
}
