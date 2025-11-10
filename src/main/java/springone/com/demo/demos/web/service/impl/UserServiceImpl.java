package springone.com.demo.demos.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import springone.com.demo.demos.web.POJO.DTO.CreateUserDto;
import springone.com.demo.demos.web.POJO.DTO.LoginDTO;
import springone.com.demo.demos.web.POJO.DTO.VerificationCodeDTO;
import springone.com.demo.demos.web.POJO.VO.LoginVO;
import springone.com.demo.demos.web.POJO.VO.UserListVo;
import springone.com.demo.demos.web.POJO.VO.UserVo;
import springone.com.demo.demos.web.common.JwtProperties;
import springone.com.demo.demos.web.common.Result;
import springone.com.demo.demos.web.common.BaseException;
import springone.com.demo.demos.web.dao.UserMapper;
import springone.com.demo.demos.web.service.UserService;
import springone.com.demo.demos.web.POJO.entity.User;
import springone.com.demo.demos.web.utile.JwtUtil;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

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
//        System.out.println(password);
        if (!password.equals(user.getPassword())) {
            throw new BaseException("密码错误");
        }

        // 修改登录时间
        userMapper.updateLoginTime(user.getId(), LocalDateTime.now());

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
    public void sendSmsCode(String phoneNumber) {

        SecureRandom secureRandom = new SecureRandom();
        int sixDigitNumber = secureRandom.nextInt(899999) + 100000;

        String cacheKey = "sms_code:" + phoneNumber;
        redisTemplate.opsForValue().set(cacheKey, String.valueOf(sixDigitNumber), 60, TimeUnit.SECONDS);

        log.info("发送验证码{}",String.valueOf(sixDigitNumber));
        return;
    }

    @Override
    public void updateUserInfo(CreateUserDto userDto) {
        userMapper.update(userDto);
    }

    @Override
    public void updatePassword(int id, String password) {
        userMapper.updatePassword(id,password);
    }

    @Override
    public List<UserListVo> selectAll() {
        return userMapper.findAll();
    }

    @Override
    public LoginVO loginByCode(VerificationCodeDTO verificationCodeDTO) {
        String key = "sms_code:" + verificationCodeDTO.getPhone();
        String code = redisTemplate.opsForValue().get(key);
        if (!code.equals(verificationCodeDTO.getCode())){
            throw new BaseException("验证码错误");
        }
        return userMapper.selectByPhone(verificationCodeDTO.getPhone());
    }
}
