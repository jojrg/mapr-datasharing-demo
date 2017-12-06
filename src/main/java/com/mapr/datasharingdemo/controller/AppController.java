package com.mapr.datasharingdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	public static final Logger logger = LoggerFactory.getLogger(AppController.class);

	//@RequestMapping("/")
	String home(ModelMap modal) {
		logger.info("AppController::home");
		modal.addAttribute("title","MapR Data Sharing Demo");
		return "indexrule";
		//return "indexuser";
	}

	//@RequestMapping("/partials/{page}")
	String partialHandler(@PathVariable("page") final String page) {

		logger.info("AppController::partialHandler for page: " + page);
		return page;
	}


	@RequestMapping("/md")
	String mdHome(ModelMap modal) {
		logger.info("AppController::md");
		modal.addAttribute("title","MapR Data Sharing Demo");
		return "mdindex";
		//return "indexuser";
	}


}
