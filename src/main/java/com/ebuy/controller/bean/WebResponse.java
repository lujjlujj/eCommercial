package com.ebuy.controller.bean;

/**
 * Created by Terry on 17-3-27.
 */
public class WebResponse {

    public static final String CODE_ERROR = "1";

    public static final String CODE_SUCCESS = "0";

    private String code;

    private String description;

    private Object returnValue;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }
}
