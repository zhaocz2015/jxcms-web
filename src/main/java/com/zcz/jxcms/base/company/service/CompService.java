package com.zcz.jxcms.base.company.service;

import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcz.jxcms.base.company.entity.Company;
import com.zcz.web.core.dao.DaoSupport;
import com.zcz.web.core.data.RsPage;

@Service
public class CompService {

	@Autowired
	DaoSupport dao;

	public RsPage findCompsByPage(Map<String, Object> params) throws Exception {
		int pageIndex = Integer.valueOf((String) params.get("page"));
		int pageSize = Integer.valueOf((String) params.get("rows"));
		return dao.findForPage("CompMapper.findCompsByPage", params, new RowBounds(pageIndex, pageSize));
	}

	public void addComp(Company comp) throws Exception {
		comp.setId(UUID.randomUUID().toString());
		dao.save("CompMapper.addComp", comp);
	}

	public void editComp(Company comp) throws Exception {
		dao.update("CompMapper.editComp", comp);
	}

	public void rmvComp(Company comp) throws Exception {
		dao.delete("CompMapper.rmvComp", comp);
	}

}
