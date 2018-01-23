package com.fantasy.eleven.controller;

import com.fantasy.eleven.model.UserDO;
import com.fantasy.eleven.service.UserService;
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
    @Resource
    private UserService userService;

    /**
     * Find all user list.
     *
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
}
