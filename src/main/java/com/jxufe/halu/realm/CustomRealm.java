package com.jxufe.halu.realm;

import com.jxufe.halu.model.User;
import com.jxufe.halu.service.IUserService;
import com.jxufe.halu.service.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;
    /**
     * 用户授权认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userId = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        simpleAuthenticationInfo.setRoles(userService.queryRoleByID(userId));
        simpleAuthenticationInfo.setStringPermissions(userService.queryPermissionByID(userId));
        return simpleAuthenticationInfo;
    }

    /**
     * 用户登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userId = authenticationToken.getPrincipal().toString();
        User user = userService.findUserById(userId);
        if(user == null){
            throw new AuthenticationException("用户不存在");
        }
        AuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo(user.getUserID(),user.getPassword(),null,getName());
        return  authorizationInfo;
    }
}
