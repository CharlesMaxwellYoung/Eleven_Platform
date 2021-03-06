package com.fantasy.eleven.test;

import com.fantasy.eleven.model.UserDO;
import com.fantasy.eleven.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Fantasy on 2018/1/19.
 *
 * @author Fantasy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:config/applicationContext.xml")
public class ServiceTest {
    @Resource
    private UserService userService;

    @Test
    public void select() throws Exception {
        UserDO userDO = new UserDO();
        userDO.setId(new Integer("1"));
        List<UserDO> userModelList = userService.findListUser(null);
        for (UserDO s : userModelList) {
            System.out.println(s.getUserPassword());
        }
    }
}
