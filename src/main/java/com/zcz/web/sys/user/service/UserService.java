package com.zcz.web.sys.user.service;

import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcz.web.core.dao.DaoSupport;
import com.zcz.web.core.data.RsPage;
import com.zcz.web.core.utils.security.Digests;
import com.zcz.web.core.utils.security.Encodes;
import com.zcz.web.sys.user.entity.SysUser;

@Service
public class UserService {

	/** 加密方法 */
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8; // 盐长度

	@Autowired
	private DaoSupport dao;

	public SysUser findUserByLoginname(String loginname) throws Exception {
		return (SysUser) dao.findForObject("UserMapper.findUserByLoginname", loginname);
	}

	public RsPage findUsersByPage(Map<String, Object> params) throws Exception {
		int pageIndex = Integer.valueOf((String) params.get("page"));
		int pageSize = Integer.valueOf((String) params.get("rows"));
		return dao.findForPage("UserMapper.findUsersByPage", params, new RowBounds(pageIndex, pageSize));
	}

	public void addUser(SysUser user) throws Exception {
		SysUser tmpUser = findUserByLoginname(user.getLoginname());
		if (tmpUser != null) {
			throw new Exception("用户已存在");
		}

		user.setId(UUID.randomUUID().toString());
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));
		user.setPassword(Encodes.encodeHex(Digests.sha1(user.getPassword().getBytes(), salt, HASH_INTERATIONS)));
		dao.save("UserMapper.addUser", user);
	}

	public void editUser(SysUser user) throws Exception {
		dao.update("UserMapper.editUser", user);
	}

	public void editPwd(SysUser user) throws Exception {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));
		user.setPassword(Encodes.encodeHex(Digests.sha1(user.getPassword().getBytes(), salt, HASH_INTERATIONS)));
		dao.update("UserMapper.editPwd", user);
	}

	public void rmvUser(SysUser user) throws Exception {
		dao.delete("UserMapper.rmvUser", user);
	}

}
