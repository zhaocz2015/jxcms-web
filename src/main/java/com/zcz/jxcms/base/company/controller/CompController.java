package com.zcz.jxcms.base.company.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcz.jxcms.base.company.entity.Company;
import com.zcz.jxcms.base.company.service.CompService;
import com.zcz.web.core.base.BaseController;
import com.zcz.web.core.data.RsPage;
import com.zcz.web.core.utils.ServletUtils;

@Controller
@RequestMapping("/comp")
public class CompController extends BaseController {

	@Autowired
	CompService compService;

	@RequestMapping("/compEntry")
	public String compEntry() {
		return "jxcms/base/compList";
	}

	@RequestMapping("/compForm")
	public String compForm() {
		return "jxcms/base/compForm";
	}

	@RequestMapping("/compList")
	@ResponseBody
	public RsPage CompList(HttpServletRequest request) throws Exception {
		Map<String, Object> params = ServletUtils.getParameters(request);
		return compService.findCompsByPage(params);
	}

	@RequestMapping("/addComp")
	@ResponseBody
	public Object addComp(Company comp) {
		try {
			compService.addComp(comp);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

	@RequestMapping("/editComp")
	@ResponseBody
	public Object editComp(Company comp) {
		try {
			compService.editComp(comp);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

	@RequestMapping("/rmvComp")
	@ResponseBody
	public Object rmvComp(Company comp) {
		try {
			compService.rmvComp(comp);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

}
