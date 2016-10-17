package com.zcz.jxcms.base.provider.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcz.jxcms.base.provider.entity.Provider;
import com.zcz.jxcms.base.provider.service.ProvService;
import com.zcz.web.core.base.BaseController;
import com.zcz.web.core.data.RsPage;
import com.zcz.web.core.utils.ServletUtils;

@Controller
@RequestMapping("/prov")
public class ProvController extends BaseController {

	@Autowired
	ProvService provService;

	@RequestMapping("/provEntry")
	public String provEntry() {
		return "jxcms/base/provList";
	}

	@RequestMapping("/provForm")
	public String provForm() {
		return "jxcms/base/provForm";
	}

	@RequestMapping("/provList")
	@ResponseBody
	public RsPage ProvList(HttpServletRequest request) throws Exception {
		Map<String, Object> params = ServletUtils.getParameters(request);
		return provService.findProvsByPage(params);
	}

	@RequestMapping("/addProv")
	@ResponseBody
	public Object addProv(Provider prov) {
		try {
			provService.addProv(prov);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

	@RequestMapping("/editProv")
	@ResponseBody
	public Object editProv(Provider prov) {
		try {
			provService.editProv(prov);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

	@RequestMapping("/rmvProv")
	@ResponseBody
	public Object rmvProv(Provider prov) {
		try {
			provService.rmvProv(prov);
			return renderSuccess("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("操作失败，错误：" + e.getMessage());
		}
	}

}
