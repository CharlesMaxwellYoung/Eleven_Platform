package com.fantasy.eleven.service.impl;

import com.fantasy.eleven.dao.UserDao;
import com.fantasy.eleven.model.UserModel;
import com.fantasy.eleven.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
