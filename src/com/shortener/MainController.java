package com.shortener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shortener.db.DBOperator;

 
@Controller
public class MainController {
	
	@RequestMapping(value="/addtest", method=RequestMethod.GET)
	public String getTest() {
		System.out.println("addtestijg");
		return "addtest";
	}
	
	@RequestMapping(value="/ShServer/add", method=RequestMethod.POST)
	public @ResponseBody String addUrl(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getParameter("url"));
		return DBOperator.addUrl(request.getParameter("url"));
	}
    
    @RequestMapping(value="/{name}", method=RequestMethod.GET)
    public String findOwner(@PathVariable("name") String name, Model model) {
    	model.addAttribute("link", DBOperator.getUrl(name));
    	return "shortened";
    }    
    
    @RequestMapping(value="/hello", method=RequestMethod.GET)
    public String findOwner() {
    	return "hello";
    }  
    
}