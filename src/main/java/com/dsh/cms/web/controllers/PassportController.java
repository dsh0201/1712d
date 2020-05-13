package com.dsh.cms.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.dsh.cms.domain.User;
import com.dsh.cms.service.UserService;
import com.dsh.cms.util.CMSException;
import com.dsh.cms.util.CMSResult;
/**
 * 
    * @ClassName: PassportController
    * @Description: 系统入口
    * @date 2020年4月9日
    *
 */
@Controller
@RequestMapping("passport")
public class PassportController {
	@Autowired
	private UserService userService;
	/**
	 * 
	    * @Title: reg
	    * @Description: 去注册页面
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@GetMapping("reg")
	public String reg() {
		return "passport/reg";
	}
	@PostMapping("reg")
	@ResponseBody
	public CMSResult<User> register(Model model,User user) {
		CMSResult<User> result = new CMSResult<User>();
		try {
			userService.register(user);
			result.setCode(200);//错误码
			result.setMsg("恭喜注册成功,请登录");
		} catch (CMSException e) {
			// 捕获自定义异常
			e.printStackTrace();
			result.setCode(500);//错误码
			result.setMsg(e.getMessage());//错误消息
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode(505);//错误码
			result.setMsg("未知错误，请联系管理员");//错误消息
		}
		return result;
	}
	/**
	 * 
	    * @Title: checkUserName
	    * @Description: 校验姓名
	    * @param @param username
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	@RequestMapping("checkUserName")
	@ResponseBody
	public boolean checkUserName(String username) {
		return null==userService.getUserByName(username);
	}
	/**
	 * 
	    * @Title: login
	    * @Description: 注册用户去登陆页面
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@GetMapping("login")
	public String login() {
		return "passport/login";
	}
	/**
	 * 
	    * @Title: login
	    * @Description: 注册用户登录
	    * @param @param user
	    * @param @param session
	    * @param @return    参数
	    * @return CMSResult<User>    返回类型
	    * @throws
	 */
	@PostMapping("login")
	@ResponseBody
	public CMSResult<User> login(User user,HttpSession session){
		CMSResult<User> result = new CMSResult<User>();
		try {
			User u = userService.login(user);
			result.setCode(200);
			result.setMsg("登录成功");
			//放session
			session.setAttribute("user", u);
		} catch (CMSException e) {
			// 捕获自定义异常
			e.printStackTrace();
			result.setCode(500);//错误码
			result.setMsg(e.getMessage());//错误消息
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode(505);//错误码
			result.setMsg("未知错误，请联系管理员");//错误消息
		}
		return result;
	}
	/**
	 * 
	    * @Title: logout
	    * @Description: 注销用户
	    * @param @param session
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("logout")
	@ResponseBody
	public boolean logout(HttpSession session) {
		session.invalidate();
		return true;
	}
	/**
	 * 
	    * @Title: loginAdmin
	    * @Description: 跳转到管理员登录页面
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@GetMapping("admin/login")
	public String loginAdmin() {
		return "passport/login_admin";
	}
	/**
	 * 
	    * @Title: adminLogin
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param user
	    * @param @param session
	    * @param @return    参数
	    * @return CMSResult<User>    返回类型
	    * @throws
	 */
	@PostMapping("admin/login")
	@ResponseBody
	public CMSResult<User> adminLogin(User user,HttpSession session){
		CMSResult<User> result = new CMSResult<User>();
		try {
			User u = userService.adminLogin(user);
			result.setCode(200);
			result.setMsg("登录成功");
			//放session
			session.setAttribute("admin", u);
		} catch (CMSException e) {
			// 捕获自定义异常
			e.printStackTrace();
			result.setCode(500);//错误码
			result.setMsg(e.getMessage());//错误消息
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode(505);//错误码
			result.setMsg("未知错误，请联系管理员");//错误消息
		}
		return result;
	}
}
