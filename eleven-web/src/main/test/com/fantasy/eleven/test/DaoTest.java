package com.fantasy.eleven.test;

import com.fantasy.eleven.dao.UserDao;
import com.fantasy.eleven.model.UserDO;
import com.fantasy.eleven.realm.MyRealm;
import org.apache.log4j.Logger;
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
public class DaoTest {
    private static Logger log = Logger.getLogger(MyRealm.class);
    @Resource
    private UserDao userDao;

    /**
     * Select.
     *
     * @throws Exception the exception
     */
    @Test
    public void select() throws Exception {
        UserDO userDO = new UserDO();
        userDO.setId(new Integer("1"));
        List<UserDO> userModelList = userDao.select(null);
        for (UserDO s : userModelList) {
            log.info("[DaoTest] [select]" + s.getUserName());
            System.out.println("[DaoTest] [select]" + s.getUserName());
        }

    }

    /**
     * Insert.
     *
     * @throws Exception the exception
     */
    @Test
    public void insert() throws Exception {
        UserDO userDO = new UserDO();
        userDO.setRoleId(1);
        userDO.setUserName("userDemo");
        userDO.setUserPassword("userPassword");
        userDO.setUserShowName("userShowNameDemo");
        Boolean isSert = userDao.insert(userDO);
        System.out.println(("[DaoTest] [update]" + isSert));
    }

    /**
     * Update.
     *
     * @throws Exception the exception
     */
    @Test
    public void update() throws Exception {
        UserDO userDO = new UserDO();
        userDO.setId(2);
        Boolean isUpdate = userDao.update(userDO);
        System.out.println(("[DaoTest] [update]" + isUpdate));
    }

    /**
     * Delete.
     *
     * @throws Exception the exception
     */
    @Test
    public void delete() throws Exception {
        UserDO userDO = new UserDO();
        userDO.setId(1);
        System.out.println(("[DaoTest] [update]" + userDao.delete(userDO)));
    }

    /**
     * Count.
     *
     * @throws Exception the exception
     */
    @Test
    public void count() throws Exception {
        System.out.println(("[DaoTest] [update]" + userDao.userCount()));
    }
}
