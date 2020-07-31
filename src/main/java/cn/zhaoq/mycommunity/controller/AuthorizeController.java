package cn.zhaoq.mycommunity.controller;

import cn.zhaoq.mycommunity.dao.UserDao;
import cn.zhaoq.mycommunity.domain.User;
import cn.zhaoq.mycommunity.dto.AccessTokenDto;
import cn.zhaoq.mycommunity.dto.GithubUser;
import cn.zhaoq.mycommunity.provider.GithubProvider;
import cn.zhaoq.mycommunity.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider provider;
    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.redirect.uri}")
    private String redirect_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Autowired
    private UserService userService;

    @GetMapping("callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){

        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(client_id);
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri(redirect_id);
        accessTokenDto.setClient_secret(client_secret);

        String token = provider.getToken(accessTokenDto);
       // System.out.println(token);
        GithubUser githubUser = provider.getGithubUser(token);
        System.out.println(githubUser);

        //判断用户是否为空
        if(githubUser!=null){

//            //存入session中
//            request.getSession().setAttribute("username",githubUser.getLogin());
            //将数据存入数据库
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getLogin());
            user.setAccountId(String.valueOf(githubUser.getId()));

            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatar_url(githubUser.getAvatar_url());

            //将用户存入数据库
            userService.saveOrFlush(user);

            //将数据存放到cookie中
            response.addCookie(new Cookie("token",user.getToken()));

            return "redirect:/";  //重定向到index页面
        }else{
            return "redirect:/";
        }

    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        //删除cookie和session
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }
}
