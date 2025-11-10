package springone.com.demo.demos.web.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import springone.com.demo.demos.web.POJO.entity.LoginLog;

import java.time.LocalDateTime;

@Mapper
public interface LogMapper {

    @Insert("insert into loginlog (username,name,phone,ip,login_time,status)" +
            "VALUES" +
            "(#{username},#{name},#{phone},#{ip},#{loginTime},#{status})")
    void insert(LoginLog loginLog);

}
