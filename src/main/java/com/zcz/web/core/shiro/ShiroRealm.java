package com.zcz.web.core.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zcz.web.core.exception.CaptchaException;
import com.zcz.web.core.utils.security.Encodes;
import com.zcz.web.sys.user.entity.SysUser;
import com.zcz.web.sys.user.service.UserService;

/**
 * shiro权限认证
 * 
 * @author itdragons 2016-06-28 01:22:18
 */
public class ShiroRealm extends AuthorizingRealm {

	private static Logger LOGGER = LoggerFactory.getLogger(ShiroRealm.class);

	@Autowired
	private UserService userSevice;
	// @Autowired
	// private SysRoleService sysRoleService;
	// @Autowired
	// private SysResourceService sysResourceService;

	/**
	 * Shiro登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		LOGGER.info("Shiro开始登录认证");
		UserCaptchaToken token = (UserCaptchaToken) authcToken;
		// 校验验证码
		if (doCaptchaValidate(token)) {
			try {
				SysUser sysUser = userSevice.findUserByLoginname(token.getUsername());

				// 账号不存在
				if (sysUser == null) {
					new UnknownAccountException();
					return null;
				}
				// 账号禁用
				// if (Const.USER_TATUS_OFF.equals(sysUser.getUserStatus())) {
				// new DisabledAccountException();
				// return null;
				// }
				ShiroUser shiroUser = new ShiroUser(sysUser.getId(), sysUser.getLoginname(), sysUser.getUsername());
				// 认证缓存信息
				byte[] salt = Encodes.decodeHex(sysUser.getSalt());
				return new SimpleAuthenticationInfo(shiroUser, sysUser.getPassword().toCharArray(), ByteSource.Util.bytes(salt), getName());
			} catch (Exception e) {
				throw new AuthenticationException(e.getMessage());
			}
		} else {
			return null;
		}

	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LOGGER.info("Shiro开始权限认证");
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> permCodeSet = new HashSet<String>();

		// Map<String, String> roleMap = new HashMap<String, String>();
		// List<Map> roleIdNameList =
		// sysRoleService.findRoleIdAndNameByUserId(shiroUser.userId);
		// for (Map roleIdNameMap : roleIdNameList) {// 遍历角色
		// // 赋予角色
		// String roleName = (String) roleIdNameMap.get("roleName");
		// info.addRole(roleName);
		//
		// // 赋予权限
		// String roleId = (String) roleIdNameMap.get("roleId");
		// List<String> permCodeList =
		// sysResourceService.findPermCodeListByRoleId(roleId,
		// Const.RESOURCE_TATUS_ON);
		// for (String permCode : permCodeList) {
		// if (StringUtil.isNotBlanks(permCode)) {
		// permCodeSet.add(permCode);
		// }
		// }
		//
		// roleMap.put(roleId, roleName);
		// }
		// shiroUser.roleMap = roleMap;

		// 把principals放session中 key=userId value=principals
		SecurityUtils.getSubject().getSession().setAttribute(shiroUser.userId, SecurityUtils.getSubject().getPrincipals());

		info.addStringPermissions(permCodeSet);
		return info;
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(UserService.HASH_ALGORITHM);
		matcher.setHashIterations(UserService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

	/**
	 * 验证码校验
	 * 
	 * @param token
	 * @return boolean
	 */
	protected boolean doCaptchaValidate(UserCaptchaToken token) {
		String captcha = (String) SecurityUtils.getSubject().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
			throw new CaptchaException("验证码错误！");
		}
		return true;
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}
}
