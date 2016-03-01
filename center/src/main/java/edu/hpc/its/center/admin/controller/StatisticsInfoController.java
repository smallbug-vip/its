package edu.hpc.its.center.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.hpc.its.center.webservice.CenterInfoService;
import edu.hpc.its.center.webservice.CenterInfoServiceImpl;

@Controller
@Scope("prototype")
@RequestMapping("/admin/")
public class StatisticsInfoController {

	CenterInfoService service = new CenterInfoServiceImpl();

	@RequestMapping("info.do")
	public String index() {

		return "back/info/index";
	}

	@RequestMapping("getInfo.do")
	public String getInfo(HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		String[] str = null;
		try {
			JsonNode n1 = mapper.readTree(service.getAllExpInfo());
			str = new String[n1.size()];
			for (int i = 0; i < n1.size(); i++) {
				str[i] = n1.get(i).get("expId").toString().substring(1, n1.get(i).get("expId").toString().length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(service.getLight2Time(str));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
