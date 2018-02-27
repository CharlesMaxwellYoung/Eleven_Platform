package com.fantasy.eleven.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Fantasy on 2018/2/27.
 *
 * @author Fantasy
 */
@Component
public class CommonConfig {
    @Value("${id_token.pub}")
    private String pubToken;

    public String getPubToken() {
        return pubToken;
    }

    public void setPubToken(String pubToken) {
        this.pubToken = pubToken;
    }
}
