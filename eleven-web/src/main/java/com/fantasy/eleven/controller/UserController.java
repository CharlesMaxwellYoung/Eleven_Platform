package com.fantasy.eleven.controller;

import com.fantasy.eleven.model.UserModel;
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
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("/findAll")
    public List<UserModel> findAllUser(@RequestParam("id") String id) {
        UserModel userModel = new UserModel();
        userModel.setId(new Integer(id));
        return userService.findAllStudent(userModel);
    }
}
