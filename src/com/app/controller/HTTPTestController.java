package com.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class HTTPTestController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@ResponseBody
	@RequestMapping(value="/testPost",method = RequestMethod.POST)
	public String testPost(HttpServletRequest request,@RequestBody Map a) {
		log.info("received new POST:[]");
		return "post";
	}
	
	@RequestMapping(value="/testGet")
	public String testGet(HttpServletRequest request) {
		log.info("index mapping 'Get'");
		return "hello";
	}

}
