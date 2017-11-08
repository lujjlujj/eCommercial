package com.ebuy.controller;

import com.ebuy.controller.bean.WebEventData;
import com.ebuy.controller.bean.WebResponse;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * Created by Terry on 17-3-11.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/web/event")
public class LogEventController {

    Logger log = LoggerFactory.getLogger(LogEventController.class);

    private HttpClient httpClient = new HttpClient();

    private static String INDEX_NAME = "process-event-log";

    private static String TYPE_NAME = "process";

    private static String URL = "http://115.28.47.100:9200/";

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addEventData(@RequestBody WebEventData webEventData) {
        log.info("Received a request to add event data: {task id:" + webEventData.getTaskId() +
                ", Task Name: " + webEventData.getTaskName() +
                ", Priority: " + webEventData.getPriority() +
                ", Action By: " + webEventData.getActionBy() +
                ", Start Date: " + webEventData.getStartDate() +
                ", Completion Date:" + webEventData.getCompletionDate() + "}");
        WebResponse response = new WebResponse();
        try {
            PostMethod postMethod = new PostMethod(URL + INDEX_NAME + "/"
                    + TYPE_NAME + "?pretty");
            JSONObject propertyObject = new JSONObject();
            propertyObject.put("taskId", UUID.randomUUID());
            propertyObject.put("taskName", webEventData.getTaskName());
            propertyObject.put("priority", webEventData.getPriority());
            propertyObject.put("startDate", webEventData.getStartDate());
            if (!StringUtils.isEmpty(webEventData.getCompletionDate())) {
                propertyObject.put("completionDate", webEventData.getCompletionDate());
            }
            propertyObject.put("actionBy", webEventData.getActionBy());
            StringRequestEntity requestEntity = new StringRequestEntity(
                    propertyObject.toString(), "application/json", "UTF-8");
            postMethod.setRequestEntity(requestEntity);
            httpClient.executeMethod(postMethod);
            response.setCode(WebResponse.CODE_ERROR);
            if (!StringUtils.isEmpty(postMethod.getResponseBodyAsString())) {
                JSONObject result = new JSONObject(postMethod.getResponseBodyAsString());
                if (result.get("created").toString().equals("true")) {
                    response.setCode(WebResponse.CODE_SUCCESS);
                }
            }

        } catch (Exception e) {
            log.error("Failed to submit the data to ELK.", e);
            response.setCode(WebResponse.CODE_ERROR);
        }


        return response;
    }
}
