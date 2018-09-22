package com.duofuen.elab.rest;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.duofuen.elab.util.Const.Rest.*;

public class BaseResponse {

    private static final Logger LOGGER = LogManager.getLogger();

    // success - null ; fail - reason;
    private String resultMessage;
    // default : success - 00 ; fail - 99
    private String resultCode;
    // decided by rest api
    private Object result;

    private BaseResponse(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public BaseResponse(String resultCode, String resultMessage, Object result) {
        this(resultCode, resultMessage);
        this.result = result;
    }

    private BaseResponse(Object result) {
        this.resultCode = SUCCESS;
        this.resultMessage = "";
        this.result = result;
    }

    public static BaseResponse success(Object result) {
        LOGGER.info("===>Restful 接口调用成功, {}", JSON.toJSONString(result));
        return new BaseResponse(result);
    }

    public static BaseResponse fail(String failMsg) {
        if (failMsg == null) {
            failMsg = "未知错误";
        }
        LOGGER.info("===>Restful 接口调用失败! 原因: {}", failMsg);
        return new BaseResponse(FAIL, failMsg);
    }

    public static BaseResponse packResultBody(Object rb, String nullMsg) {
        if (rb == null) {
            return BaseResponse.fail(nullMsg);
        } else {
            return BaseResponse.success(rb);
        }
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
