package com.app.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.task.Task;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@EnableAutoConfiguration
public class MainController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	//@Value("${spring.resources.static-locations}")
	String staticlocations;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String index() {
		log.info("index mapping {}",staticlocations);
		return "index";
	}
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,Model model) {
		log.info("index mapping 'index'");
		return "index";
	}
	@RequestMapping(value="/home.do")
	public String test(HttpServletRequest request,Model model) {
		log.info("index mapping 'home.do'");
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value="/createNewOrder",method = RequestMethod.POST)
	public ResultObj createNewOrder(HttpServletRequest request,String orderData) {
		log.info("received new order:[{}]",orderData);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/schedule",method = RequestMethod.POST)
	public ResultObj schedule(HttpServletRequest request,String orderData) {
		log.info("received new order:[{}]",orderData);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/arrive",method = RequestMethod.POST)
	public ResultObj arrive(HttpServletRequest request,String orderData) {
		log.info("received new order:[{}]",orderData);
		return null;
	}
	
	

}