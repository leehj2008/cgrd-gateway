package com.app.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@WebFilter(urlPatterns="/*")
public class SecurityFilter implements Filter {

    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "CURR_USER";
    String [] excludeURIContains={"/error","/login","/logout","/index","/api"};
    String [] excludeURIEnd={".js",".css",".jpg",".gif",".png",".bmp",".json",".html"};
    String [] equal={"/"};
    Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		String uri=httpRequest.getRequestURI();
		log.info("uri="+uri);
		
        if (session.getAttribute(SESSION_KEY) != null||isExclude(uri)){
        	chain.doFilter(request, response);
            return;
        }else {

    		log.info("reject");
	        // 跳转登录
	        httpResponse.sendRedirect("/");
        }
	}
	private boolean isExclude(String uri){
		for(String u : excludeURIContains){
			if(uri.toLowerCase().contains(u)){
				return true;
			}
		}for(String u : excludeURIEnd){
			if(uri.toLowerCase().endsWith(u)){
				return true;
			}
		}for(String u : equal){
			if(uri.equals(u)){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}