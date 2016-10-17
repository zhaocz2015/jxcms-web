package com.zcz.jxcms.base.product.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcz.jxcms.base.product.entity.Product;
import com.zcz.jxcms.base.product.service.ProdService;
import com.zcz.web.core.base.BaseController;
import com.zcz.web.core.data.RsPage;
import com.zcz.web.core.utils.ServletUtils;

@Controller
@RequestMapping("/prod")
public class ProdController extends BaseController {

	@Autowired
	ProdService prodService;

	@RequestMapping("/prodEntry")
	public String prodEntry() {
		return "jxcms/base/prodList";
	}

	@RequestMapping("/prodForm")
	public String prodForm() {
		return "jxcms/base/prodForm";
	}

	@RequestMapping("/prodList")
	@ResponseBody
	public RsPage ProdList(HttpServletRequest request) throws Exception {
		Map<String, Object> params = ServletUtils.getParameters(request);
		return prodService.findProdsByPage(params);
	}

	@RequestMapping("/addProd")
	@ResponseBody
	public Object addProd(Product prod) {
		try {
			prodService.addProd(prod);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

	@RequestMapping("/editProd")
	@ResponseBody
	public Object editProd(Product prod) {
		try {
			prodService.editProd(prod);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

	@RequestMapping("/rmvProd")
	@ResponseBody
	public Object rmvProd(Product prod) {
		try {
			prodService.rmvProd(prod);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

}
