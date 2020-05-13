package com.dsh.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dsh.cms.domain.User;
/**
 * 
    * @ClassName: AdminInterceptor
    * @Description: 个人中心拦截器
    * @date 2020年4月10日
    *
 */
public class AdminInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//如果已登录则不拦截，否则拦截
		HttpSession session = request.getSession(false);
		if(null!=session) {
			//如果有session，则从session中拿登录对象，已登录就放行
			User user = (User)session.getAttribute("admin");
			if(null!=user) {
				return true;
			}
		}
		request.setAttribute("msg", "请登录！");
		request.getRequestDispatcher("/WEB-INF/view/passport/login_admin.jsp").forward(request, response);
		return false;
	}
}
