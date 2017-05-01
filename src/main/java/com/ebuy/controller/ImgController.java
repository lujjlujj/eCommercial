package com.ebuy.controller;

import com.ebuy.controller.bean.WebEventData;
import com.ebuy.controller.bean.WebOauthLinkData;
import com.ebuy.controller.bean.WebResponse;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terry on 17-5-2.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/web/img")
public class ImgController {

    @RequestMapping(value = "/oauth/icon", method = RequestMethod.GET)
    @ResponseBody
    public List<WebOauthLinkData> getOauthIcons() {
        List<WebOauthLinkData> webOauthLinkDatas = new ArrayList<WebOauthLinkData>();
        WebOauthLinkData webOauthLinkData = new WebOauthLinkData();
        webOauthLinkData.setIconName("github-logo.png");
        webOauthLinkData.setUrl("/login/github");
        webOauthLinkDatas.add(webOauthLinkData);
        return webOauthLinkDatas;
    }
}
