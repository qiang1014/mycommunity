package cn.zhaoq.mycommunity.dao;

import cn.zhaoq.mycommunity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    /**
     * 根据token获取User对象
     *
     * @param token
     * @return
     */

    public User findByToken(String token);

    //public User findById()

}
