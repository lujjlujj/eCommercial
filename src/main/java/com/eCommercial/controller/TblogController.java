package com.ecommercial.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Terry on 17-3-11.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/web/article")
public class TblogController {

    @RequestMapping("/")
    @ResponseBody
    public String getArticles() {
        return "Hello World!";
    }

}
