package com.server.model.entity;

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
        @Index(name = "userId", columnList = "userId"),
})
@TableName(value = "user_detail")
@Data
@ToString
@Entity
public class UserDetail implements Serializable {
    @Id
    @TableId(type = IdType.AUTO)
    @Range
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    @Range(max = 1)
    private Integer status;

    @NotNull
    @Length(max = 50)
    private String phone;

    @Length(max = 20)
    @NotBlank
    private String postCode;

    @Length(max = 500)
    @NotBlank
    private String address;

    @TableField(fill = FieldFill.INSERT)
    private String createdTime;

    private String createdBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedTime;

    private String updatedBy;
}
