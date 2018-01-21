package com.fantasy.eleven.realm;

import com.fantasy.eleven.model.UserModel;
import com.fantasy.eleven.service.UserService;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by Fantasy on 2018/1/21.
 *
 * @author Fantasy
 */
public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    private static Logger log = Logger.getLogger(MyRealm.class);

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = principalCollection.getPrimaryPrincipal().toString();
        log.debug("[doGetAuthorizationInfo] [username]" + userName);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = userService.getRolesByUserName(userName);
        log.debug("[doGetAuthorizationInfo] [roles]" + roles);
        Set<String> permissions = userService.getPermissionsByUserName(userName);
        log.debug("[doGetAuthorizationInfo] [permissions]" + permissions);
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = authenticationToken.getPrincipal().toString();
        UserModel userModel = userService.getUserByUserName(userName);
        if (userModel != null) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userModel.getUserName(), userModel.getUserPassword(), "a");
            log.debug("[doGetAuthenticationInfo] [authenticationInfo]" + authenticationInfo.toString());
            return authenticationInfo;
        }
        return null;
    }
}
