package test.demo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import test.demo.enums.EntityStatus;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table(indexes = {
        @Index(name = "userId", columnList = "userId"),
})
@TableName(value = "user_detail")
@Data
@ToString
public class UserDetail extends BaseEntity {
    @Id
    @TableId(type = IdType.AUTO)
    @Range
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private EntityStatus status;

    @NotNull
    @Length(max = 50)
    private String phone;

    @Length(max = 20)
    @NotBlank
    private String postCode;

    @Length(max = 500)
    @NotBlank
    private String address;
}
