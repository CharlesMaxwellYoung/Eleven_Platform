package com.fantasy.eleven.service;

import com.fantasy.eleven.model.UserModel;

import java.util.List;

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
}
