package com.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.server.enums.EntityStatus;
import com.server.component.CommonValidator;
import com.server.component.RedisComponent;
import com.server.model.PageResult;
import com.server.model.entity.User;
import com.server.model.entity.UserDetail;
import com.server.model.entity.UserInfo;

import com.server.model.mapper.UserDetailMapper;
import com.server.model.mapper.UserMapper;
import com.server.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

@Component
@Slf4j(topic = "exceptionLog")
public class UserService {
    @Autowired
    RedisComponent redisComponent;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserDetailMapper userDetailMapper;

    @Autowired
    CommonValidator commonValidator;

    private final String PERFIX = "USER:";

    @Transactional
    public Pair<Boolean, String> saveUser(UserInfo userInfo) throws Exception {
        User user           = userInfo.getUser();
        UserDetail userDetail     = userInfo.getUserDetail();
        Pair<Boolean, String> validateResult = commonValidator.validateUser(user);
        if (validateResult.getKey()) {
            return Pair.of(false, User.class + ":" + validateResult.getValue() + ":验证失败!");
        }
        String cacheKey    = this.PERFIX + user.getEmail();
        String getIfAbsent = redisComponent.get(cacheKey);
        if (getIfAbsent == null) {
            QueryWrapper<User> qw = new QueryWrapper<>();
            qw.eq("email", user.getEmail());
            qw.eq("status", EntityStatus.STATUS_ACTIVE.getValue());
            User persistentUser = userMapper.selectOne(qw);
            if (persistentUser == null) {
                try {
                    redisComponent.setIfAbsent(cacheKey);
                    int state = userMapper.insert(user);
                    if (state > 0) {
                        userDetail.setUserId(user.getId());
                        userDetail.setStatus(EntityStatus.STATUS_ACTIVE.getValue());
                        validateResult = commonValidator.validateUserDetail(userDetail);
                        if (validateResult.getKey()) {
                            throw new Exception(UserDetail.class + ":" + validateResult.getValue() + ":验证失败！");
                        }
                        state = userDetailMapper.insert(userDetail);
                        redisComponent.delate(cacheKey);
                        if (state > 0) {
                            return Pair.of(true, String.valueOf(user.getId()));
                        }
                        throw new Exception("添加用户信息失败！");
                    }
                } catch (Exception exception) {
                    log.error(exception.getMessage());
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return Pair.of(false, exception.getMessage());
                }
            }
            return Pair.of(false, "用户已经存在！");
        }
        return Pair.of(false, "错误操作！");
    }

    public Map<String, Object> userDetail(Long id, Integer status) {
        Map<String, Object> user      = Collections.emptyMap();
        String              cacheKey  = this.PERFIX + id;
        String              userCache = redisComponent.get(cacheKey);
        if (userCache != null) {
            user = JacksonUtil.toMap(userCache);
        }
        if (user.isEmpty()) {
            redisComponent.set(cacheKey, JacksonUtil.toJSONString(Collections.emptyMap()));
            Map<String, String> userInfo = userMapper.selectUserDetail(id, status);
            userCache = JacksonUtil.toJSONString(userInfo);
            if (userInfo != null) {
                redisComponent.setWithExpire(cacheKey, userCache, 1000);
            }
            user = JacksonUtil.toMap(userCache);
        }
        return user;
    }

    public PageResult getUserList(PageResult pageResult) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("status", EntityStatus.STATUS_ACTIVE);
        Page<User>  page = new Page<>(pageResult.getCurrentPage(), pageResult.getPageSize());
        IPage<User> user = userMapper.selectPage(page, qw);
        pageResult.setCurrentPage(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotal(user.getTotal());
        pageResult.setPages(user.getPages());
        pageResult.setList(user.getRecords());
        return pageResult;
    }

    public Pair<Boolean, String> updateUser(Long id, UserInfo userInfo) {
        User                  user           = userInfo.getUser();
        UserDetail            userDetail     = userInfo.getUserDetail();
        Pair<Boolean, String> validateResult = commonValidator.validateUser(user);
        if (validateResult.getKey()) {
            return Pair.of(false, User.class + ":" + validateResult.getValue() + ":验证失败！");
        }
        validateResult = commonValidator.validateUserDetail(userDetail);
        if (validateResult.getKey()) {
            return Pair.of(false, UserDetail.class + ":" + validateResult.getValue() + ":验证失败！");
        }
        return Pair.of(true, "修改成功！");
    }

    public Pair<Boolean, String> deleteUser(Long id) {
        Map<String, String> userInfo = userMapper.selectUserDetail(id, EntityStatus.STATUS_ACTIVE.getValue());
        if (userInfo == null || userInfo.isEmpty()) {
            return Pair.of(false, "用户不存在！");
        }
        Integer res = userMapper.deleteUserInfo(id);
        return Pair.of(res > 0, res > 0 ? "删除成功！" : "删除失败！");
    }
}
