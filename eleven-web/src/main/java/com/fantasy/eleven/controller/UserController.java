package com.fantasy.eleven.controller;

import com.fantasy.eleven.model.UserDO;
import com.fantasy.eleven.service.PermissionService;
import com.fantasy.eleven.service.RoleService;
import com.fantasy.eleven.service.UserService;
import com.fantasy.eleven.utils.SystemUtils;
import com.fantasy.eleven.vo.JsonResult;
import com.fantasy.eleven.vo.UserSuccessVO;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

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
     * 用户的
     *
     * @param userDO the user do
     * @return the object
     */
    @ResponseBody
    @RequestMapping(value = "/userLogin")
    public JsonResult userSignInByNameAndPassword(@RequestBody UserDO userDO) {
        JsonResult<String> jsonResult = new JsonResult<String>();
        if (userDO.getUserName() == null || userDO.getUserPassword() == null) {
            jsonResult.setSuccess(false);
            jsonResult.setError("用户名或者密码");
            return jsonResult;
        }
        Subject userSubject = SecurityUtils.getSubject();
        UsernamePasswordToken uToken = new UsernamePasswordToken(userDO.getUserName(), userDO.getUserPassword());
        try {
            userSubject.login(uToken);
        } catch (UnknownAccountException e) {
            jsonResult.setSuccess(false);
            jsonResult.setError("用户名不存在");
            return jsonResult;
        } catch (IncorrectCredentialsException e) {
            jsonResult.setSuccess(false);
            jsonResult.setError("用户密码不正确");
            return jsonResult;
        } catch (LockedAccountException e) {
            jsonResult.setSuccess(false);
            jsonResult.setError("用户被锁");
            return jsonResult;
        } catch (AuthenticationException e) {
            jsonResult.setSuccess(false);
            jsonResult.setError("其他错误");
            return jsonResult;
        }
        /**
         * 登录成功封装用户信息
         */
        UserSuccessVO userSuccessVO = new UserSuccessVO();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userSuccessVO.setUserName(userDO.getUserName());
        userSuccessVO.setSysDate(df.format(new Date()));
        JsonResult<UserSuccessVO> objectJsonResult = new JsonResult<UserSuccessVO>();
        objectJsonResult.setSuccess(true);
        objectJsonResult.setResult(userSuccessVO);

        return objectJsonResult;
    }

    /**
     * User sign out json result.
     *
     * @return the json result
     */
    @ResponseBody
    @RequestMapping("/userLoginOut")
    public JsonResult userSignOut() {
        JsonResult<String> jsonResult = new JsonResult<String>();
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        jsonResult.setSuccess(true);
        jsonResult.setResult("注销成功");
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("/getUser")
    public JsonResult<List<UserDO>> getUsers(String id) {
        JsonResult<List<UserDO>> jsonResult = new JsonResult<List<UserDO>>();
        UserDO userDO = new UserDO();
        if (id != null) {
            userDO.setId(new Integer(id));
        }
        jsonResult.setSuccess(true);
        jsonResult.setResult(userService.findListUser(userDO));
        return jsonResult;
    }


    /**
     * 用户注册
     *
     * @param userDO the user do
     * @return the string
     */
    @RequestMapping("/userSignUp")
    public String userSignUp(@RequestBody UserDO userDO) {
        if (userDO != null) {
            ByteSource salt = ByteSource.Util.bytes(userDO.getUserName());
            SimpleHash simpleHashPassword = new SimpleHash("md5", userDO.getUserPassword(), salt, 2);
            userDO.setUserPassword(simpleHashPassword.toString());
        }
        Boolean isInsertUser = userService.insertUser(userDO);
        return isInsertUser ? "Login" : "SignUp";
    }

    /**
     * 获得所有用户角色权限的所有个数
     *
     * @param request the request
     * @return the object
     */
    @ResponseBody
    @RequestMapping("/userNormalInfo")
    public JsonResult userRolePermsCounts(HttpServletRequest request) {
        JsonResult<UserSuccessVO> jsonResult = new JsonResult<UserSuccessVO>();
        List<Integer> userRolePerms = new ArrayList<Integer>();
        UserSuccessVO userSuccessVO = new UserSuccessVO();
        jsonResult.setSuccess(true);
        int userCount = userService.userCount();
        int roleCount = roleService.roleCount();
        int permissionCount = permissionService.permissionCount();
        log.debug("[userRolePermsCounts] [userCount]: " + userCount);
        userRolePerms.add(userCount);
        log.debug("[userRolePermsCounts] [roleCount]: " + roleCount);
        userRolePerms.add(roleCount);
        log.debug("[userRolePermsCounts] [permissionCount]: " + permissionCount);
        userRolePerms.add(permissionCount);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userSuccessVO.setSysDate(df.format(new Date()));
        log.debug("[userRolePermsCounts] [setSysDate]: " + df.format(new Date()));
        userSuccessVO.setIpAddress(SystemUtils.getSystemIpAddress(request));
        userSuccessVO.setUserRolePermsCounts(userRolePerms);
        jsonResult.setResult(userSuccessVO);
        return jsonResult;
    }
}
