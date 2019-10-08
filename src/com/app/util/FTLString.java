package com.app.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FTLString {
	Configuration cfg ;
    StringTemplateLoader stringLoader ;
	public FTLString (){
		 cfg = new Configuration();  
	     stringLoader = new StringTemplateLoader();  
	     cfg.setTemplateLoader(stringLoader);
	     cfg.setTemplateUpdateDelay(0);
	}
	
	public String compileString(String templateContent,Map<String,Object> data){
		stringLoader.putTemplate("strTemplate",templateContent);  
        StringWriter writer = new StringWriter();    
		try {  
	        Template template = cfg.getTemplate("strTemplate","utf-8");  
	        try {  
	            template.process(data, writer);  
	        } catch (TemplateException e) {  
	            e.printStackTrace();  
	        }   
	          
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
		return writer.toString();
	}
	
	public static void main(String[] args) {
		String template="hello,${name}";
		FTLString f = new FTLString();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name","���");
		String a = f.compileString(template, data);
		System.out.println(a);
		data.put("name","���");
		String template1="haaaa,${name}";
		
		String b = f.compileString(template1, data);
		System.out.println(b);
	}

}
