package com.dby.njxinch.action.loginManage;

import com.dby.njxinch.common.CommonConstants;
import com.dby.njxinch.services.UserService;
import com.dby.njxinch.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/loginManage")
public class LoginManageController {

    @Resource
    private UserService userService;

    /**
     * 跳转到 登录页面
     *
     * @return
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request, Model model) {
        model.addAttribute("flag", request.getParameter("flag"));
        return "login/login";
    }

    /**
     * 跳转到 登录页面
     *
     * @return
     */
    @RequestMapping(value = "/toRoleIndexPage", method = RequestMethod.POST)
    public String toRoleIndexPage(HttpServletRequest request, Model model) {

        return "login/roleIndexPage";
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public Object doLogin(HttpServletRequest request) throws Exception {
        String account = "";
        String password = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            // 判断是否存在账号cookie
            if ("account".equals(cookie.getName()) && StringUtils.isNotEmpty(cookie.getValue())) {
                account = cookie.getValue();
                cookie.setMaxAge(30 * 24 * 60 * 60);
            } else {
                account = request.getParameter("account");
            }

            if ("password".equals(cookie.getName()) && StringUtils.isNotEmpty(cookie.getValue())) {
                password = cookie.getValue();
                cookie.setMaxAge(30 * 24 * 60 * 60);
            } else {
                password = request.getParameter("password");
            }
        }
        Map<String, Object> result = userService.doLogin(request, account, password);
        return result;
    }


    /*
     * homepage
     */
    @RequestMapping(value = "/toHomepage", method = RequestMethod.POST)
    public String toHomepage(HttpServletRequest request,String roleIdenty) {
        request.getSession().setAttribute(CommonConstants.USER_SESSION_IDENTY,request.getParameter("roleIdenty"));
        return "redirect:/loginManage/againHomePage";
    }

        @RequestMapping(value = "/againHomePage", method = RequestMethod.GET)
    public String homepage(HttpServletRequest request,String roleIdenty) {
        System.out.println(111);
        return "homePage/homePage";
    }


    /**
     * 登出
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(CommonConstants.USER_SESSION_KEY);
        return "login/login";
    }

}
