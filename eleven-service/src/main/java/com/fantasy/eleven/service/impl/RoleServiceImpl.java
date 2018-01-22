package com.fantasy.eleven.service.impl;

import com.fantasy.eleven.dao.RoleDao;
import com.fantasy.eleven.model.RoleDO;
import com.fantasy.eleven.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Fantasy on 2018/1/22.
 *
 * @author Fantasy
 */
@Service("RoleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    public List<RoleDO> findListRole(RoleDO u) {
        return roleDao.select(u);
    }

    public Boolean insertRole(RoleDO u) {
        return roleDao.insert(u);
    }

    public Boolean updateRole(RoleDO u) {
        return roleDao.update(u);
    }

    public Boolean deleteRole(RoleDO u) {
        return roleDao.delete(u);
    }
}
