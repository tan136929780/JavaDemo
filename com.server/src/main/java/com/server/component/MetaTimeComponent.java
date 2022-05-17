package com.server.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据库更新时间
 */
@Component
public class MetaTimeComponent implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "createdTime", getDate());
        this.fillStrategy(metaObject, "updatedTime", getDate());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "updatedTime", getDate());
    }

    private String getDate() {
        return (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date());
    }
}
