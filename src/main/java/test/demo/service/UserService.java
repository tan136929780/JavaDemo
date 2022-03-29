package test.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import test.demo.component.CommonValidator;
import test.demo.component.RedisOperater;
import test.demo.enums.EntityStatus;
import test.demo.model.PageResult;
import test.demo.model.entity.User;
import test.demo.model.entity.UserDetail;
import test.demo.model.entity.UserInfo;
import test.demo.model.mapper.UserDetailMapper;
import test.demo.model.mapper.UserMapper;
import test.demo.utils.JacksonUtil;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

@Component
@Slf4j(topic = "exceptionLog")
public class UserService {
    @Autowired
    RedisOperater redisOperater;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserDetailMapper userDetailMapper;

    @Autowired
    CommonValidator commonValidator;

    private final String PERFIX = "USER:";

    @Transactional
    public Pair<Boolean, String> saveUser(UserInfo userInfo) throws Exception {
        User                  user           = userInfo.getUser();
        UserDetail            userDetail     = userInfo.getUserDetail();
        Pair<Boolean, String> validateResult = commonValidator.validateUser(user);
        if (validateResult.getKey()) {
            return Pair.of(false, User.class + ":" + validateResult.getValue() + ": validate failed!");
        }
        String cacheKey    = this.PERFIX + user.getEmail();
        String getIfAbsent = redisOperater.get(cacheKey);
        if (getIfAbsent == null) {
            QueryWrapper<User> qw = new QueryWrapper<>();
            qw.eq("email", user.getEmail());
            qw.eq("status", EntityStatus.STATUS_ACTIVE.getValue());
            User persistentUser = userMapper.selectOne(qw);
            if (persistentUser == null) {
                try {
                    redisOperater.setIfAbsent(cacheKey);
                    int state = userMapper.insert(user);
                    if (state > 0) {
                        userDetail.setUserId(user.getId());
                        userDetail.setStatus(EntityStatus.STATUS_ACTIVE.getValue());
                        validateResult = commonValidator.validateUserDetail(userDetail);
                        if (validateResult.getKey()) {
                            throw new Exception(UserDetail.class + ":" + validateResult.getValue() + ": validate failed!");
                        }
                        state = userDetailMapper.insert(userDetail);
                        redisOperater.delate(cacheKey);
                        if (state > 0) {
                            return Pair.of(true, String.valueOf(user.getId()));
                        }
                        throw new Exception("Add UserInfo failed!");
                    }
                } catch (Exception exception) {
                    log.error(exception.getMessage());
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return Pair.of(false, exception.getMessage());
                }
            }
            return Pair.of(false, "User already exist!");
        }
        return Pair.of(false, "Operation Error!");
    }

    public Map<String, Object> userDetail(Long id, Integer status) {
        Map<String, Object> user      = Collections.emptyMap();
        String              cacheKey  = this.PERFIX + id;
        String              userCache = redisOperater.get(cacheKey);
        if (userCache != null) {
            user = JacksonUtil.toMap(userCache);
        }
        if (user.isEmpty()) {
            redisOperater.set(cacheKey, JacksonUtil.toJSONString(Collections.emptyMap()));
            Map<String, String> userInfo = userMapper.selectUserDetail(id, status);
            userCache = JacksonUtil.toJSONString(userInfo);
            if (userInfo != null) {
                redisOperater.setWithExpire(cacheKey, userCache, 1000);
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
            return Pair.of(false, User.class + ":" + validateResult.getValue() + ": validate failed!");
        }
        validateResult = commonValidator.validateUserDetail(userDetail);
        if (validateResult.getKey()) {
            return Pair.of(false, UserDetail.class + ":" + validateResult.getValue() + ": validate failed!");
        }
        return Pair.of(true, "修改成功");
    }

    public Pair<Boolean, String> deleteUser(Long id) {
        Integer res = userMapper.deleteUserInfo(id);
        return Pair.of(res > 0, res > 0 ? "删除成功" : "删除失败");
    }
}
