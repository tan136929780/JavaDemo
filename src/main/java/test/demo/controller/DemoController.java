package test.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.demo.component.CommonValidator;
import test.demo.enums.EntityStatus;
import test.demo.enums.ReturnStatus;
import test.demo.model.ReturnResult;
import test.demo.model.entity.User;
import test.demo.model.entity.UserInfo;
import test.demo.service.UserService;
import test.demo.utils.ResponseUtil;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class DemoController {
    @Autowired
    CommonValidator commonValidator;

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ReturnResult save(@RequestBody UserInfo userInfo) throws Exception {
        Pair<Boolean, String>  saveResult = userService.saveUser(userInfo.getUser(), userInfo.getUserDetail());
        if (!saveResult.getKey()) {
            return ResponseUtil.failed(saveResult.getValue());
        }
        Map<String, String> data = Collections.singletonMap("id", saveResult.getValue());
        return ResponseUtil.success(data);
    }

    @GetMapping("/detail")
    public ReturnResult detail(@RequestBody Map<String, Long> params) {
        if (params.isEmpty() || params.get("id") <= 0) {
            return ResponseUtil.withMessage(ReturnStatus.CONDITION_ERROR.getCode(), ReturnStatus.CONDITION_ERROR.getMessage(), null);
        }
        Map<String, Object> user = userService.userDetail(params.get("id"), EntityStatus.STATUS_ACTIVE.getValue());
        return ResponseUtil.success(user);
    }
}
