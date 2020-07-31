package cn.zhaoq.mycommunity.service;

import cn.zhaoq.mycommunity.dao.UserDao;
import cn.zhaoq.mycommunity.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    /**
     * 判断用户是否存在，若存在则更新用户，不存在则保存
     * @param user
     */
    public void saveOrFlush(User user) {
        //根据账号id查询用户
        User user1 = null;
        user1 = userDao.findByAccountId(user.getAccountId());
        if(user1 == null){
            userDao.save(user);//保存用户
        }else {
            //userDao.updateUser(user.getName(),user.getToken(),user.getGmtModified(),user.getAvatar_url(),user.getAccountId()); //更新用户
            user.setId(user1.getId());
            userDao.save(user);
        }
    }
}
