package springone.com.demo.demos.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import springone.com.demo.demos.web.POJO.DTO.CreateUserDto;
import springone.com.demo.demos.web.POJO.DTO.LoginDTO;
import springone.com.demo.demos.web.POJO.VO.LoginVO;
import springone.com.demo.demos.web.POJO.VO.UserVo;
import springone.com.demo.demos.web.common.JwtProperties;
import springone.com.demo.demos.web.common.Result;
import springone.com.demo.demos.web.common.BaseException;
import springone.com.demo.demos.web.dao.UserMapper;
import springone.com.demo.demos.web.service.UserService;
import springone.com.demo.demos.web.POJO.entity.User;
import springone.com.demo.demos.web.utile.JwtUtil;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void insert(CreateUserDto createUserDto) {
        User user = new User();
        BeanUtils.copyProperties(createUserDto,user);
        userMapper.insert(user);
    }

    @Override
    public void delete(User loginVO) {

    }

    @Override
    public UserVo selectById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public Result login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        User user = userMapper.selectByUserName(username);
        if (user == null) {
            throw new BaseException("用户不存在");
        }

        password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(password);
        if (!password.equals(user.getPassword())) {
            throw new BaseException("密码错误");
        }

        LoginVO loginVO = new LoginVO();
        BeanUtils.copyProperties(user,loginVO);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());

        String token = JwtUtil.createJWT(
                jwtProperties.getSecret(),
                jwtProperties.getExpiration(),
                claims);
        loginVO.setToken(token);

        return Result.success(loginVO);
    }

    @Override
    public String sendSmsCode(String phoneNumber) {

        SecureRandom secureRandom = new SecureRandom();
        int sixDigitNumber = secureRandom.nextInt(900000) + 100000;

        return null;
    }

    @Override
    public void updateUserInfo(CreateUserDto userDto) {
        userMapper.update(userDto);
    }

    @Override
    public void updatePassword(int id, String password) {
        userMapper.updatePassword(id,password);
    }
}
