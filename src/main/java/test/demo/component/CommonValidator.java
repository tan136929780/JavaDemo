package test.demo.component;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import test.demo.model.entity.User;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class CommonValidator {
    @Resource
    Validator validator;

    public Pair<Boolean, String> validateUser(User user) {
        Set<ConstraintViolation<User>> validate = validator.validate(user);
        String                         field    = validate.size() > 0 ? validate.stream().iterator().next().getPropertyPath().toString() : "";
        if (StringUtils.isNotEmpty(field)) {
            return Pair.of(true, field);
        }

        return Pair.of(false, null);
    }
}
