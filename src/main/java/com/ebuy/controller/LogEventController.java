package com.ebuy.controller;

import com.ebuy.controller.bean.WebEventData;
import com.ebuy.controller.bean.WebResponse;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Terry on 17-3-11.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/web/event")
public class LogEventController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addEventData(@RequestBody WebEventData webEventData) {
        WebResponse response = new WebResponse();
        response.setCode(WebResponse.CODE_SUCCESS);
        return response;
    }
}
