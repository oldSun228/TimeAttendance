package com.dby.njxinch.action.homePage;

import com.dby.njxinch.common.CommonConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fgs on 2016/6/26.
 */
@Controller
@RequestMapping("/homePage")
public class HomePageController {
    /**
     * 跳转到 middleInitialPage
     * 中间初始页
     * @return
     */
    @RequestMapping(value = "/toMiddleInitial", method = RequestMethod.GET)
    public String toMiddlePage(HttpServletRequest request) {

        return "homePage/middleInitialPage";
    }

    @RequestMapping(value = "/toMainPage", method = RequestMethod.GET)
    public String toMainPage(HttpServletRequest request) {

        return "homePage/toMainPage";
    }


}
