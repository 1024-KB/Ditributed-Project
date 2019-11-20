/**
* Title: UserController.java  

* Description   

* @author xhz  

* @date 2019年10月24日  
 
 */
package com.imooc.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.pojo.AdminUser;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.utils.JsonResult;
import com.imooc.utils.PageResult;

/**
 * @author xhz
 * @description 用户控制类
 */
@Controller
@RequestMapping("users")
public class UserController extends BasicController{
	
	@Autowired
	private UserService userService;
	
	/**
	 * @name login
	 * @Description 用户登录
	 * @param 
	 * @return 
	 */
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public JsonResult userLogin(String username, String password,HttpServletRequest request, HttpServletResponse response){
		//判断用户名和密码是否为空
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			return JsonResult.errorMsg("用户名和密码不能为空");
		}
		//判断用户名和密码是否正确
		if(StringUtils.equals("imooc", username) && StringUtils.equals("imooc",password)){
			String token=UUID.randomUUID().toString();
			AdminUser user =new AdminUser(username,password,token);
			request.getSession().setAttribute("sessionUser", user);
			return JsonResult.ok();
		}
		
		return JsonResult.errorMsg("登录失败，请重新登录");
	}
	
	/**
	 * @name userList
	 * @Description 跳到用户列表
	 * @param 
	 * @return 
	 */
	@GetMapping("/showList")
	public String showList(){
		return "users/usersList";
	}
	
	/**
	 * @name list
	 * @Description 分页查询查询用户列表：1：当没有查询条件的时候，进行全表查询   2：如果有条件，就根据给定的条件进行条件查询
	 * @param user
	 * @param pageNum
	 * @return 
	 */
	@PostMapping("/list")
	@ResponseBody
	public JsonResult list(Users user, Integer pageNum){
		PageResult pageResult=userService.queryUser(user,pageNum == null ? 1 : pageNum, PAGE_SIZE);
		return JsonResult.ok(pageResult);
	}

	
	@GetMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().setAttribute("sessionToken", null);
		response.sendRedirect("/center.action");
	}
	
}
