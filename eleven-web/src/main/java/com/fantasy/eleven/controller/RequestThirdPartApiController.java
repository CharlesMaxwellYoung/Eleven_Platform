package com.fantasy.eleven.controller;

import com.alibaba.fastjson.JSON;
import com.fantasy.eleven.utils.RegexUtils;
import com.fantasy.eleven.vo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Fantasy on 2018/2/27.
 *
 * @author Fantasy
 */
@Controller
@RequestMapping("ThirdPartApi")
public class RequestThirdPartApiController {
    private final static String DOUBAN_STR = "douban";
    private final static String JU_HE = "juhe";

    @ResponseBody
    @RequestMapping("/getResult")
    public JsonResult<String> getDatasByThirdPartApi(String thirdPartUrls) {
        Boolean isUrlStr = RegexUtils.checkURL(thirdPartUrls);
        JsonResult<String> jsonResult = new JsonResult<String>();
        RestTemplate restTemplate = new RestTemplate();
        if (isUrlStr) {
            String fullUrls = pushApiKeyForUrls(thirdPartUrls);
            String responseEntity = restTemplate.getForObject(fullUrls, String.class);
            JSON.parse(responseEntity);
            jsonResult.setResult(responseEntity);
        }
        return jsonResult;
    }

    private String pushApiKeyForUrls(String thirdPartUrls) {
        if (thirdPartUrls.contains(DOUBAN_STR)) {
            return thirdPartUrls + "?apikey=0b2bdeda43b5688921839c8ecb20399b";
        } else if (thirdPartUrls.contains(JU_HE)) {
            return thirdPartUrls + "?key=e797c5d6ccd36ae12f073ca69297c185";
        } else {
            return thirdPartUrls;
        }
    }
}
