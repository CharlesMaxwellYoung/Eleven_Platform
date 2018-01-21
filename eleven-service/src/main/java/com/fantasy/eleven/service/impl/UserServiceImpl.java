package com.fantasy.eleven.service.impl;

import com.fantasy.eleven.dao.UserDao;
import com.fantasy.eleven.model.UserModel;
import com.fantasy.eleven.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by Fantasy on 2018/1/19.
 *
 * @author Fantasy
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public List<UserModel> findAllStudent(UserModel u) {
        return userDao.select(u);
    }

    public Set<String> getRolesByUserName(String userName) {
        return userDao.getRolesByUserName(userName);
    }

    public Set<String> getPermissionsByUserName(String userName) {
        return userDao.getPermissionsByUserName(userName);
    }

    public UserModel getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }
}
