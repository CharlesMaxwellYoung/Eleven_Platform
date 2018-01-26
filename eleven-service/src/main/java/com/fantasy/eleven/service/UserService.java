package com.fantasy.eleven.service;

import com.fantasy.eleven.model.UserDO;
import com.fantasy.eleven.model.UserModel;

import java.util.List;
import java.util.Set;

/**
 * Created by Fantasy on 2018/1/19.
 *
 * @author Fantasy
 */
public interface UserService {
    /**
     * Find list user list.
     *
     * @param u the u
     * @return the list
     */
    List<UserDO> findListUser(UserDO u);

    /**
     * Insert user boolean.
     *
     * @param u the u
     * @return the boolean
     */
    Boolean insertUser(UserDO u);

    /**
     * Update user boolean.
     *
     * @param u the u
     * @return the boolean
     */
    Boolean updateUser(UserDO u);

    /**
     * Delete user boolean.
     *
     * @param u the u
     * @return the boolean
     */
    Boolean deleteUser(UserDO u);

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
     * Gets user by user name.
     *
     * @param userName the user name
     * @return the user by user name
     */
    UserModel getUserByUserName(String userName);


    /**
     * User count integer.
     *
     * @return the integer
     */
    Integer userCount();
}
