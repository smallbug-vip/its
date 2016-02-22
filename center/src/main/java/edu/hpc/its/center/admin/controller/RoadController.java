package edu.hpc.its.center.admin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping("/admin/")
public class RoadController {

	@RequestMapping("road.do")
	public String index(){
		
		return "back/road/index";
	}
}
