package test.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.demo.component.CommonValidator;
import test.demo.model.ReturnResult;
import test.demo.model.entity.User;
import test.demo.utils.ResponseUtil;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class DemoController {
    @Autowired
    CommonValidator commonValidator;

    @GetMapping("/save")
    public ReturnResult save(@RequestParam(value = "name", defaultValue = "") User user) {
        Pair<Boolean, String> validateResult = commonValidator.validateUser(user);
        if (!validateResult.getKey()) {
            return ResponseUtil.failed(null);
        }
        Map<String, Long> data = Collections.singletonMap("id", user.getId());
        return ResponseUtil.success(data);
    }
}
