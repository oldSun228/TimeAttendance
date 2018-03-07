package com.dby.njxinch.action.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author fgs
 * @Date 2017/3/14 19:48
 * 类说明：获取字典的共通
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(value = "/demo1", method = RequestMethod.GET)
    public String demo1(HttpServletRequest request) {

        return "demo/mxGraph";
    }

    @RequestMapping(value = "/demo2", method = RequestMethod.GET)
    public String demo2(HttpServletRequest request) {

        return "demo/demo2";
    }
}
