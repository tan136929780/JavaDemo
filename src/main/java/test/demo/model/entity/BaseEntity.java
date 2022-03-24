package test.demo.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 公共字段
 */
@Data
public abstract class BaseEntity implements Serializable {
    @TableField(fill = FieldFill.INSERT)
    private String createdTime;

    @NotBlank
    private String createdBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedTime;

    @NotBlank
    private String updatedBy;
}
