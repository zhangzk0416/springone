package springone.com.demo.demos.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import springone.com.demo.demos.web.dao.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
//    @Insert("insert into user (username,password) values (#{username},#{password})")
//    void insert(LoginVO loginVO);

}
