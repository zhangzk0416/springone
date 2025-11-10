package springone.com.demo.demos.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import springone.com.demo.demos.web.POJO.DTO.CreateUserDto;
import springone.com.demo.demos.web.POJO.DTO.LoginDTO;
import springone.com.demo.demos.web.POJO.VO.LoginVO;
import springone.com.demo.demos.web.POJO.VO.UserListVo;
import springone.com.demo.demos.web.POJO.VO.UserVo;
import springone.com.demo.demos.web.POJO.entity.User;
import springone.com.demo.demos.web.common.Result;

import java.util.List;

public interface UserService extends IService<User> {

    void insert(CreateUserDto createUserDto);

    void delete(User loginVO);

    UserVo selectById(Long id);

    void deleteById(Long id);

    Result login(LoginDTO loginDTO);

    String sendSmsCode(String phoneNumber);

    void updateUserInfo(CreateUserDto userDto);

    void updatePassword(int id, String password);

    List<UserListVo> selectAll();
}
