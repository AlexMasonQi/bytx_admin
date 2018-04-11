package com.bytx.admin.realm;

import com.bytx.admin.entity.Permission;
import com.bytx.admin.entity.Role;
import com.bytx.admin.entity.User;
import com.bytx.admin.service.UserQueryService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 * @description 用户登录权限验证
 * @date 2018.03.28 09:52
 */
public class UserRealm extends AuthorizingRealm
{
    @Autowired
    @Lazy
    private UserQueryService userQueryService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        String currentLoginName = (String) principalCollection.getPrimaryPrincipal();
        List<String> userPermissions = new ArrayList<>();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        User user = userQueryService.getUserByName(currentLoginName);
        if (user != null)
        {
            Role role = userQueryService.getRoleByUserId(Integer.valueOf(user.getId().toString()));
            List<Permission> permissionList = userQueryService.getPermissionsById(role.getId());
            user.setUserRole(role.getId());
            authorizationInfo.addRole(role.getRoleName());
            for (var permission : permissionList)
            {
                userPermissions.add(permission.getPermissionName());
            }
            authorizationInfo.addStringPermissions(userPermissions);
        }
        else
        {
            throw new AuthorizationException();
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        System.out.println("[开始认证session ID:]==>" + SecurityUtils.getSubject().getSession().getId());
        //获取用户输入的账号
        String userName = (String) authenticationToken.getPrincipal();

        //获取Session
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        User user = userQueryService.getUserByName(userName);

        //如果用户名为空，则抛出该异常
        if (user == null)
        {
            throw new UnknownAccountException();
        }

        Role role = userQueryService.getRoleByUserId(Integer.valueOf(user.getId().toString()));
        user.setUserRole(role.getId());

        session.setAttribute("user", user);

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassword(), getName());

        return simpleAuthenticationInfo;
    }
}
