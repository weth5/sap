package com.whx.dubboService;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whx.pojo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 借助此realm完成认证和授权信息的获取和封装
 * @author Administrator
 *
 */
@Service
public class ShiroUserRealm extends AuthorizingRealm{
	@Reference
	private UserService userService;
	/**
	 * 设置凭证匹配器,即告诉securitymanage用什么算法,加密几次
	 */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		//构建凭证匹配对象
		HashedCredentialsMatcher cMatcher=new HashedCredentialsMatcher();
		//设置加密算法
		cMatcher.setHashAlgorithmName("MD5");
		//设置加密次数
		cMatcher.setHashIterations(1);
		super.setCredentialsMatcher(cMatcher);
	}
	//授权
	/*@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//1.获取登录用户信息，例如用户id
		User user=(User)principals.getPrimaryPrincipal();
		Integer userId=user.getId();
		//2.基于用户id获取用户拥有的角色id(sys_user_roles)
		List<Integer> roleIds= sysUserRoleDao.findRoleIdsByUserId(userId);
		if(roleIds==null||roleIds.size()==0) throw new AuthorizationException();
		//3.基于角色id获取菜单id(sys_role_menus)
		Integer[] array={};
		List<Integer> menuIds= sysRoleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(array));
		if(menuIds==null||menuIds.size()==0) throw new AuthorizationException();
		//4.基于菜单id获取权限标识(sys_menus)
		List<String> permissions= sysMenuDao.findPermissions(menuIds.toArray(array));
		//5.对权限标识信息进行封装并返回,用set是为了去重
		Set<String> set=new HashSet<>();
		for(String per:permissions){
			if(!StringUtils.isEmpty(per)){
				set.add(per);
			}
		}
		SimpleAuthorizationInfo info= new SimpleAuthorizationInfo();
		info.setStringPermissions(set);
		return info;//返回给授权管理器

	}*/
	//负责认证信息的获取和封装
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1:获取用户名
		//token.getPrincipal();
		UsernamePasswordToken upToken=(UsernamePasswordToken) token;
		String username = upToken.getUsername();
		System.out.println(username+"是username");
		System.out.println(userService+"userService");
		//2:基于用户名查询用户信息
		User user = userService.findUserByName(username);
		//3:校验用户信息(用户是否存在,是否禁用)
		if (user==null)throw new UnknownAccountException();
		if(user.getValid()==0)throw new LockedAccountException();
		//将数据存储到redis中
		//4:封装数据并返回
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(
				user, //principal(身份)
				user.getPassword(), //hashedCredentials,加密密码
				credentialsSalt, //credentialsSalt,盐值,但要封装成ByteSource对象
				getName());//realmName,当前类名
		return info;//返回给认证管理器
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}
}
