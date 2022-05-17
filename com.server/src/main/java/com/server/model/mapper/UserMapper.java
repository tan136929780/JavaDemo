package com.server.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.model.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT u.id, u.user_name, u.gender, u.age, u.email, d.address, d.phone, d.post_code FROM user AS u LEFT JOIN user_detail AS d ON u.id=d.user_id WHERE u.status = #{status} AND u.id = #{id}")
    Map<String, String> selectUserDetail(Long id, Integer status);

    @Update("Update user AS u LEFT JOIN user_detail AS d ON u.id=d.user_id SET u.status = 0, d.status = 0 WHERE u.id = #{id}")
    Integer deleteUserInfo(Long id);
}
