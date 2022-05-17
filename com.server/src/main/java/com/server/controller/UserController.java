package com.server.controller;

import com.server.enums.EntityStatus;
import com.server.enums.ReturnStatus;
import com.server.model.ReturnResult;
import com.server.component.CommonValidator;
import com.server.model.PageResult;
import com.server.model.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.server.service.UserService;
import com.server.utils.ResponseUtil;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    CommonValidator commonValidator;

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ReturnResult save(@RequestBody UserInfo userInfo) throws Exception {
        Pair<Boolean, String> userResult = userService.saveUser(userInfo);
        if (!userResult.getKey()) {
            return ResponseUtil.failed(userResult.getValue());
        }
        Map<String, String> data = Collections.singletonMap("id", userResult.getValue());
        return ResponseUtil.success(data);
    }

    @PostMapping("/update")
    public ReturnResult update(@RequestBody UserInfo userInfo) {
        Long id = userInfo.getUser().getId();
        if (id <= 0) {
            return ResponseUtil.withMessage(ReturnStatus.CONDITION_ERROR.getCode(), ReturnStatus.CONDITION_ERROR.getMessage(), "ID 无效");
        }
        Pair<Boolean, String> userResult = userService.updateUser(id, userInfo);
        if (!userResult.getKey()) {
            return ResponseUtil.failed(userResult.getValue());
        }
        Map<String, String> data = Collections.singletonMap("id", userResult.getValue());
        return ResponseUtil.success(data);
    }

    @PostMapping("/detail")
    public ReturnResult detail(@RequestBody Map<String, Long> params) {
        if (params.isEmpty() || !params.containsKey("id") || params.get("id") <= 0) {
            return ResponseUtil.withMessage(ReturnStatus.CONDITION_ERROR.getCode(), ReturnStatus.CONDITION_ERROR.getMessage(), null);
        }
        Map<String, Object> user = userService.userDetail(params.get("id"), EntityStatus.STATUS_ACTIVE.getValue());
        return ResponseUtil.success(user);
    }

    @PostMapping("/list")
    public ReturnResult list(@RequestBody Map<String, Long> params) {
        if (params.isEmpty() || !params.containsKey("currentPage") || params.get("currentPage") <= 0) {
            return ResponseUtil.withMessage(ReturnStatus.CONDITION_ERROR.getCode(), ReturnStatus.CONDITION_ERROR.getMessage(), null);
        }
        PageResult pageResult = new PageResult();
        pageResult.setCurrentPage(params.get("currentPage"));
        if (params.containsKey("pageSize")) {
            pageResult.setPageSize(params.get("pageSize"));
        }
        PageResult userList = userService.getUserList(pageResult);
        return ResponseUtil.success(userList);
    }

    @PostMapping("/delete")
    public ReturnResult delete(@RequestBody Map<String, Long> params) {
        if (params.isEmpty() || !params.containsKey("id") || params.get("id") <= 0) {
            return ResponseUtil.withMessage(ReturnStatus.CONDITION_ERROR.getCode(), ReturnStatus.CONDITION_ERROR.getMessage(), null);
        }
        Pair<Boolean, String> userResult = userService.deleteUser(params.get("id"));
        if (!userResult.getKey()) {
            return ResponseUtil.failed(userResult.getValue());
        }
        return ResponseUtil.success(null);
    }
}
