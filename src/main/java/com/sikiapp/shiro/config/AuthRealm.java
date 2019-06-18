/**
 * projectName: spring-boot-in-action
 * fileName: AuthRealm.java
 * packageName: com.sikiapp.springbootinaction.config
 * date: 2019-05-27 下午12:07
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.config;

import com.sikiapp.shiro.dao.ShiroSampleDao;
import com.sikiapp.shiro.entity.User;
import com.sikiapp.shiro.service.AuthorizationService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @className: AuthRealm
 * @packageName: com.sikiapp.springbootinaction.config
 * @description: 认证领域
 * @author: Robert
 * @data: 2019-05-27 下午12:07
 * @version: V1.0
 **/
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private ShiroSampleDao shiroSampleDao;

    /**
     * 认证回调函数,登录时调用
     * 首先根据传入的用户名获取User信息；然后如果user为空，那么抛出没找到帐号异常UnknownAccountException；
     * 如果user找到但锁定了抛出锁定异常LockedAccountException；最后生成AuthenticationInfo信息，
     * 交给间接父类AuthenticatingRealm使用CredentialsMatcher进行判断密码是否匹配，
     * 如果不匹配将抛出密码错误异常IncorrectCredentialsException；
     * 另外如果密码重试此处太多将抛出超出重试次数异常ExcessiveAttemptsException；
     * 在组装SimpleAuthenticationInfo信息时， 需要传入：身份信息（用户名）、凭据（密文密码）、盐（username+salt），
     * CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException { UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        // 获取密文密码
//        User user = shiroSampleDao.findUserByName(username);
        User user = AuthorizationService.USERS_CACHE.get(username);

        // principal：认证的实体信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPwd(), super.getName());
        // 如果设置了加盐，则使用加密算法
        if (user.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        }
        return info;
    }

    /**
     * 只有需要验证权限时才会调用, 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.在配有缓存的情况下，只加载一次.
     * 如果需要动态权限,但是又不想每次去数据库校验,可以存在ehcache中.自行完善
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User)super.getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取数据库的记录
        Set<String> roles = shiroSampleDao.getRolesByUserId(user.getUid());
        Set<String> perms = shiroSampleDao.getPermsByUserId(user.getUid());
        authorizationInfo.setRoles(roles);
        authorizationInfo.addStringPermissions(perms);
        return authorizationInfo;
    }

}