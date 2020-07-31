package cn.zhaoq.mycommunity.dao;

import cn.zhaoq.mycommunity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    /**
     * 根据token获取User对象
     *
     * @param token
     * @return
     */

    public User findByToken(String token);


    /**
     * 根据AccountId查询用户
     * @return
     */
    //@Query(value = "select *from user where accountId=?",nativeQuery=true)
    public User findByAccountId(String accountId);


    /**
     * 刷新
     * @param name
     * @param token
     * @param gmtModified
     * @param avatar_url
     * @param accountId
     */
//    @Query(value = "update User set name = ?,token= ?,gmtCreate= ?,avatar_url= ? where accountId= ?")
//    @Modifying
//    public void updateUser(String name, String token, Long gmtModified, String avatar_url, String accountId);
}
