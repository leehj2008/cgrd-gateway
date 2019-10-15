package com.app.login;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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

import com.app.dao.User;
import com.app.dao.UserDao;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@EnableAutoConfiguration
public class LoginController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	UserDao userdao;
	@ResponseBody
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request,String username,String password) {
		User dbuser = userdao.getUser(username);
		Boolean isValidUser=false;
		if(dbuser!=null && dbuser.getPasswd().equals(password)){
			request.getSession().setAttribute(SecurityFilter.SESSION_KEY, dbuser);
			isValidUser = true;
		}
		if(isValidUser){
			return "true";
		}else{
			return "无效用户名或密码";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/api/getUserByWinUser")
	public User login(HttpServletRequest request,String winuser) {
		User dbuser = userdao.getUserByWinUser(winuser);
		return dbuser;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request,String username,String password) {
		request.getSession().removeAttribute(SecurityFilter.SESSION_KEY);
		log.info("logout");
		return "index";
	}


}