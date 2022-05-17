package com.client.model.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Table(indexes = {
        @Index(name = "userName", columnList = "userName"),
        @Index(name = "email", columnList = "email"),
})
@TableName(value = "user")
@Data
@ToString
@Entity
public class User implements Serializable {
    @Id
    @TableId(type = IdType.AUTO)
    @Range
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(max = 50)
    private String userName;

    @NotNull
    @Range(max = 1)
    private Integer gender;

    @Range(min = 1, max = 150)
    @NotNull
    private Integer age;

    private String email;

    @TableField(fill = FieldFill.INSERT)

    @NotNull
    @Range(max = 1)
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private String createdTime;

    private String createdBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedTime;

    private String updatedBy;
}
