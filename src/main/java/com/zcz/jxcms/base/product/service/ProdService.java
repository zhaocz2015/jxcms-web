package com.zcz.jxcms.base.product.service;

import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcz.jxcms.base.product.entity.Product;
import com.zcz.web.core.dao.DaoSupport;
import com.zcz.web.core.data.RsPage;

@Service
public class ProdService {

	@Autowired
	DaoSupport dao;

	public RsPage findProdsByPage(Map<String, Object> params) throws Exception {
		int pageIndex = Integer.valueOf((String) params.get("page"));
		int pageSize = Integer.valueOf((String) params.get("rows"));
		return dao.findForPage("ProdMapper.findProdsByPage", params, new RowBounds(pageIndex, pageSize));
	}

	public void addProd(Product prod) throws Exception {
		prod.setId(UUID.randomUUID().toString());
		dao.save("ProdMapper.addProd", prod);
	}

	public void editProd(Product prod) throws Exception {
		dao.update("ProdMapper.editProd", prod);
	}

	public void rmvProd(Product prod) throws Exception {
		dao.delete("ProdMapper.rmvProd", prod);
	}

}
