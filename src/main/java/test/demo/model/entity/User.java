package test.demo.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import test.demo.enums.EntityStatus;
import test.demo.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Table(indexes = {
        @Index(name = "userName", columnList = "userName"),
        @Index(name = "email", columnList = "email"),
})
@TableName(value = "user")
@Data
@ToString
public class User extends BaseEntity {
    @Id
    @TableId(type = IdType.AUTO)
    @Range
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(max = 50)
    private String userName;

    @NotNull
    private Gender gender;

    @Range(min = 1, max = 150)
    @NotNull
    private Integer age;

    private String email;

    @TableField(fill = FieldFill.INSERT)

    @NotNull
    private EntityStatus status;
}
