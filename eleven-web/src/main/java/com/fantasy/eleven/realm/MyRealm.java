package com.fantasy.eleven.realm;

import com.fantasy.eleven.model.UserDO;
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
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;
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

    /**
     * 用于权限验证
     *
     * @param principalCollection
     * @return
     */
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

    /**
     * 用于登陆验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        UserDO userDO = new UserDO();
        userDO.setUserName(userName);
        List<UserDO> userDOList = userService.findListUser(userDO);
        UserDO userFirstModel = userDOList.get(0);
        String pubToken = "m94oOLawLKA6Fai0jowy4vmlOvyvijoBF5iknKX4XbMPSfHw7XldUrJDQGTZijvotXDYopUBk9quq20DVh0Pn5NDGPYMAMBpuWo";
        if (userFirstModel != null) {
            ByteSource salt = ByteSource.Util.bytes(userFirstModel.getUserName());
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userFirstModel.getUserName(), userFirstModel.getUserPassword(), salt, this.getName());
            log.debug("[doGetAuthenticationInfo] [authenticationInfo] " + authenticationInfo.toString());
            return authenticationInfo;
        }
        return null;
    }
}
