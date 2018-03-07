package com.dby.njxinch.interceptor;

import com.dby.njxinch.common.CommonConstants;
import com.dby.njxinch.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




public class InterceptorController implements HandlerInterceptor {

	private Logger logger = Logger.getLogger(InterceptorController.class.getName());
	
	private List<String> uncheckUrls;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("Pre-handle");
		User userBean = (User)request.getSession().getAttribute(CommonConstants.USER_SESSION_KEY);
		String requestUrl = request.getRequestURI();
		if (uncheckUrls.contains(requestUrl)) {
			return true;
		} else {
			// TODO 做拦截的事情
			if (null != userBean) {
				return true;
			}else{
				String url;
				url = request.getScheme() + "://";
				url += request.getHeader("host");
				logger.log(Level.INFO, "user not login");
				
				response.sendRedirect(url+"/TimeAttendance/loginManage/toLogin?flag="+"interceptor");
				return false;
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Post-handle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("After completion handle");
	}

	public List<String> getUncheckUrls() {
		return uncheckUrls;
	}

	public void setUncheckUrls(List<String> uncheckUrls) {
		this.uncheckUrls = uncheckUrls;
	}
	
	

}
