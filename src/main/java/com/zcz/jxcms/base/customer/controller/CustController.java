package com.zcz.jxcms.base.customer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcz.jxcms.base.customer.entity.Customer;
import com.zcz.jxcms.base.customer.service.CustService;
import com.zcz.web.core.base.BaseController;
import com.zcz.web.core.data.RsPage;
import com.zcz.web.core.utils.ServletUtils;

@Controller
@RequestMapping("/cust")
public class CustController extends BaseController {

	@Autowired
	CustService custService;

	@RequestMapping("/custEntry")
	public String custEntry() {
		return "jxcms/base/custList";
	}

	@RequestMapping("/custForm")
	public String custForm() {
		return "jxcms/base/custForm";
	}

	@RequestMapping("/custList")
	@ResponseBody
	public RsPage CustList(HttpServletRequest request) throws Exception {
		Map<String, Object> params = ServletUtils.getParameters(request);
		return custService.findCustsByPage(params);
	}

	@RequestMapping("/addCust")
	@ResponseBody
	public Object addCust(Customer cust) {
		try {
			custService.addCust(cust);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

	@RequestMapping("/editCust")
	@ResponseBody
	public Object editCust(Customer cust) {
		try {
			custService.editCust(cust);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

	@RequestMapping("/rmvCust")
	@ResponseBody
	public Object rmvCust(Customer cust) {
		try {
			custService.rmvCust(cust);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

}
