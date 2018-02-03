package com.fantasy.eleven.service.impl;

import com.fantasy.eleven.dao.UserDao;
import com.fantasy.eleven.model.UserDO;
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

    public List<UserDO> findListUser(UserDO u) {
        return userDao.select(u);
    }

    public Boolean insertUser(UserDO u) {
        List<UserDO> userDOList = userDao.select(u);
        if (userDOList.size() > 0) {
            return false;
        }
        return userDao.insert(u);
    }

    public Boolean updateUser(UserDO u) {
        return userDao.update(u);
    }

    public Boolean deleteUser(UserDO u) {
        return userDao.delete(u);
    }

    public Set<String> getRolesByUserName(String userName) {
        return userDao.getRolesByUserName(userName);
    }

    public Set<String> getPermissionsByUserName(String userName) {
        return userDao.getPermissionsByUserName(userName);
    }

    public Integer userCount() {
        return userDao.userCount();
    }
}
