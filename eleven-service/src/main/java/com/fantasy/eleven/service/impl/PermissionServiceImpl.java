package com.fantasy.eleven.service.impl;

import com.fantasy.eleven.dao.PermissionDao;
import com.fantasy.eleven.model.PermissionDO;
import com.fantasy.eleven.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Fantasy on 2018/1/22.
 *
 * @author Fantasy
 */
@Service("PermissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;

    public List<PermissionDO> findListPermission(PermissionDO u) {
        return permissionDao.select(u);
    }

    public Boolean insertPermission(PermissionDO u) {
        return permissionDao.insert(u);
    }

    public Boolean updatePermission(PermissionDO u) {
        return permissionDao.update(u);
    }

    public Boolean deletePermission(PermissionDO u) {
        return permissionDao.delete(u);
    }

    public Integer permissionCount() {
        return permissionDao.permissionCount();
    }

}
