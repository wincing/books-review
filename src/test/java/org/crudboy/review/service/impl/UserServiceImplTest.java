package org.crudboy.review.service.impl;

import junit.framework.TestCase;
import org.crudboy.review.service.UserService;
import org.crudboy.review.service.exception.BussinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceImplTest extends TestCase {

    @Resource
    private UserService userService;

    @Test
    public void testCreateUser() {
        try {
            userService.createUser("imooc_1", "12345678", "xixi");
        } catch (BussinessException e) {
            e.printStackTrace();
        }
    }
}