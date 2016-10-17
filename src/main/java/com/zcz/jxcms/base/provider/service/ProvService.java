package com.zcz.jxcms.base.provider.service;

import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcz.jxcms.base.provider.entity.Provider;
import com.zcz.web.core.dao.DaoSupport;
import com.zcz.web.core.data.RsPage;

@Service
public class ProvService {

	@Autowired
	DaoSupport dao;

	public RsPage findProvsByPage(Map<String, Object> params) throws Exception {
		int pageIndex = Integer.valueOf((String) params.get("page"));
		int pageSize = Integer.valueOf((String) params.get("rows"));
		return dao.findForPage("ProvMapper.findProvsByPage", params, new RowBounds(pageIndex, pageSize));
	}

	public void addProv(Provider prov) throws Exception {
		prov.setId(UUID.randomUUID().toString());
		dao.save("ProvMapper.addProv", prov);
	}

	public void editProv(Provider prov) throws Exception {
		dao.update("ProvMapper.editProv", prov);
	}

	public void rmvProv(Provider prov) throws Exception {
		dao.delete("ProvMapper.rmvProv", prov);
	}

}
