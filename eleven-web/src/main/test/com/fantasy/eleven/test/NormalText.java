package com.fantasy.eleven.test;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * Created by Fantasy on 2018/1/31.
 *
 * @author Fantasy
 */
public class NormalText {
    @Test
    public void DateTest() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date()));
    }
}
