package test.demo.annotation;


import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumericRange {
    /**
     * 验证失败的消息
     */
    String message() default "校验失败";

    /**
     * 分组的内容
     */
    Class<?>[] groups() default {};

    /**
     * 错误验证的级别
     */
    Class<? extends Payload>[] payload() default {};


    double min() default 0;

    double max() default Double.MAX_VALUE;
}
