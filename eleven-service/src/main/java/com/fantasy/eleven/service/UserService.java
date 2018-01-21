package com.fantasy.eleven.service;

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
     * Find all student list.
     *
     * @param u the u
     * @return the list
     */
    List<UserModel> findAllStudent(UserModel u);

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
}
