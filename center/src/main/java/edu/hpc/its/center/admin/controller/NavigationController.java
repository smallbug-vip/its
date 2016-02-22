package edu.hpc.its.center.admin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping("/admin/")
public class NavigationController {

	/**
	 * 主界面
	 * 
	 * @timestamp Feb 18, 2016 12:50:21 PM
	 * @return
	 */
	@RequestMapping("index.do")
	public String index() {
		System.out.println("--------------");
		return "back/index";
	}

	/**
	 * 菜单
	 * 
	 * @timestamp Feb 18, 2016 12:50:29 PM
	 * @return
	 */
	@RequestMapping("menu.do")
	public String menu() {

		return "back/menu";
	}

	/**
	 * 操作区
	 * 
	 * @timestamp Feb 18, 2016 12:54:11 PM
	 * @return
	 */
	@RequestMapping("main.do")
	public String main() {

		return "back/main";
	}
}
