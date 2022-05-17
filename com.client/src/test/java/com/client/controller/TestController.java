package com.client.controller;


import com.client.enums.EntityStatus;
import com.client.enums.Gender;
import com.client.task.TestTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.client.model.PageResult;
import com.client.model.entity.User;
import com.client.model.entity.UserDetail;
import com.client.model.entity.UserInfo;
import com.client.service.UserService;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


@SpringBootTest
@Slf4j(topic = "exceptionLog")
public class TestController {
    @Autowired
    UserService userService;

    @Test
    public void testThread() {
        Callable callable = new TestTask();
        for (int i = 0; i < 10; i++) {
            try {
                FutureTask task = new FutureTask<>(callable);
                new Thread(task, "子线程" + i).start();

                System.out.println("子线程返回值：" + task.get() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testInsert() throws Exception {
        UserInfo              userInfo   = getUser();
        Pair<Boolean, String> saveResult = userService.saveUser(userInfo);
        System.out.println(saveResult);
    }

    @Test
    public void testDetail() {
        Map<String, Object> user = userService.userDetail(1L, EntityStatus.STATUS_ACTIVE.getValue());
        System.out.println(user);
    }

    @Test
    public void testList() {
        PageResult pageResult = new PageResult();
        PageResult userList   = userService.getUserList(pageResult);
        System.out.println(userList);
    }

    public UserInfo getUser() {
        String name = "A·Da";
        User   user = new User();
        user.setUserName(name);
        user.setAge(30);
        user.setGender(Gender.GENDER_MALE.getValue());
        user.setEmail(StringUtils.lowerCase(user.getUserName()) + "@58.com");
        user.setCreatedBy("admin");
        user.setUpdatedBy("admin");
        user.setStatus(EntityStatus.STATUS_ACTIVE.getValue());
        //-----
        UserDetail userDetail = new UserDetail();
        userDetail.setAddress("北京市 朝阳区 酒仙桥 58同城");
        userDetail.setPhone("15200000000");
        userDetail.setPostCode("000001");
        userDetail.setCreatedBy("admin");
        userDetail.setUpdatedBy("admin");
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        userInfo.setUserDetail(userDetail);
        return userInfo;
    }
}
