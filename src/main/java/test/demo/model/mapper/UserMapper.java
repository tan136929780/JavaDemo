package test.demo.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import test.demo.model.entity.User;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT u.id, u.user_name, u.gender, u.age, u.email, d.address, d.phone, d.post_code FROM user AS u LEFT JOIN user_detail AS d ON u.id=d.user_id WHERE u.status = #{status} AND u.id = #{id}")
    Map<String, String> selectUserDetail(Long id, Integer status);
}
