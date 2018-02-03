package com.fantasy.eleven.dao;

import com.fantasy.eleven.dao.base.BaseDao;
import com.fantasy.eleven.model.UserDO;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Fantasy on 2018/1/19.
 *
 * @author yy
 */
@Repository
public interface UserDao extends BaseDao<UserDO> {
    /**
     * Gets roles by user name.
     *
     * @param userName the user name
     * @return the roles by user name
     */
    Set<String> getRolesByUserName(String userName);

    /**
     * Gets permissions by user name.
     *
     * @param userName the user name
     * @return the permissions by user name
     */
    Set<String> getPermissionsByUserName(String userName);


    /**
     * User count integer.
     *
     * @return the integer
     */
    Integer userCount();
}
