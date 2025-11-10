package springone.com.demo.demos.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import springone.com.demo.demos.web.POJO.DTO.CreateUserDto;
import springone.com.demo.demos.web.POJO.VO.UserVo;
import springone.com.demo.demos.web.POJO.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{
//    @Insert("insert into user (username,password) values (#{username},#{password})")
//    void insert(LoginVO loginVO);

    @Select("select username,name,phone,id_card,sex,age from user where id = #{id}")
    UserVo selectById(Long id);

    @Update("update user set is_delete=1 where id = #{id}")
    void deleteById(Long id);

    @Select("select id,username,name,phone,id_card,password from user where username=#{username}")
    User selectByUserName(String username);

    @Update("update user set username = #{username},name=#{name},phone=#{phone},id_card=#{idCard},sex=#{sex},age=#{age} where id = #{id}")
    void update(CreateUserDto userDto);

//    void insert(User user);
}
