package com.zcz.jxcms.base.customer.service;

import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcz.jxcms.base.customer.entity.Customer;
import com.zcz.web.core.dao.DaoSupport;
import com.zcz.web.core.data.RsPage;

@Service
public class CustService {

	@Autowired
	DaoSupport dao;

	public RsPage findCustsByPage(Map<String, Object> params) throws Exception {
		int pageIndex = Integer.valueOf((String) params.get("page"));
		int pageSize = Integer.valueOf((String) params.get("rows"));
		return dao.findForPage("CustMapper.findCustsByPage", params, new RowBounds(pageIndex, pageSize));
	}

	public void addCust(Customer comp) throws Exception {
		comp.setId(UUID.randomUUID().toString());
		dao.save("CustMapper.addCust", comp);
	}

	public void editCust(Customer comp) throws Exception {
		dao.update("CustMapper.editCust", comp);
	}

	public void rmvCust(Customer comp) throws Exception {
		dao.delete("CustMapper.rmvCust", comp);
	}

}
