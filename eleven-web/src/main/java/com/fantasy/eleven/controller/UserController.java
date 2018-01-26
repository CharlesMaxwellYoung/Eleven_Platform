package com.fantasy.eleven.controller;

import com.fantasy.eleven.model.UserDO;
import com.fantasy.eleven.service.UserService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Fantasy on 2018/1/20.
 *
 * @author Fantasy
 */
@Controller
@RequestMapping("user")
public class UserController {

    private static Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;

    /**
     * @param id the id
     * @return the list
     */
    @ResponseBody
    @RequestMapping("/findAll")
    public List<UserDO> findAllUser(@RequestParam("id") String id) {
        UserDO userDO = new UserDO();
        userDO.setId(new Integer(id));
        return userService.findListUser(userDO);
    }


    /**
     * 注册用户
     *
     * @return the string
     */
    @RequestMapping("/signUp")
    public String signUpUser() {
        return "SignUp";
    }

    /**
     * 进入登录页面
     *
     * @return the string
     */
    @RequestMapping("/login")
    public String userLogin() {
        return "Login";
    }

    /**
     * User sign in by name and password.
     *
     * @param userDO the user do
     */
    @RequestMapping("/userLogin")
    public void userSignInByNameAndPassword(UserDO userDO) {
        Subject userSubject = SecurityUtils.getSubject();
        UsernamePasswordToken uToken = new UsernamePasswordToken(userDO.getUserName(), userDO.getUserPassword());
        try {
            userSubject.login(uToken);
        } catch (UnknownAccountException e) {
            log.debug("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            log.debug("用户密码不正确");
        } catch (LockedAccountException e) {
            log.debug("用户被锁");
        } catch (AuthenticationException e) {
            log.debug("其他错误");
        }
    }


    /**
     * 用户注册
     *
     * @param userDO the user do
     */
    @RequestMapping("/userSignUp")
    public String userSignUp(UserDO userDO) {
        if (userDO != null) {
            ByteSource salt = ByteSource.Util.bytes(userDO.getUserName());
            SimpleHash simpleHashPassword = new SimpleHash("md5", userDO.getUserPassword(), salt, 2);
            userDO.setUserPassword(simpleHashPassword.toString());
        }
        Boolean isInsertUser = userService.insertUser(userDO);
        return isInsertUser ? "Login" : "SignUp";
    }
}
