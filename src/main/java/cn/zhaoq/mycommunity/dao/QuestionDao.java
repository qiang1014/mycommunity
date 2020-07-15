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
}
