package com.fantasy.eleven.test;

import com.alibaba.fastjson.JSON;
import com.fantasy.eleven.controller.RoleController;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Fantasy on 2018/1/31.
 *
 * @author Fantasy
 */
public class NormalText {

    @Test
    public void DateTest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.douban.com/v2/movie/coming_soon?apikey=0b2bdeda43b5688921839c8ecb20399b";
        String testStr = "foo";
        Pattern pattern = Pattern.compile("douban");
        Matcher matcher = pattern.matcher(url);
        System.out.println(matcher.lookingAt());

        System.out.println(url.contains("douban"));
        //String responseEntity = restTemplate.getForObject(url, String.class);
        //System.out.println(JSON.parse(responseEntity));
    }
}
