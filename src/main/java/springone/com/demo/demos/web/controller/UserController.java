package springone.com.demo.demos.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import springone.com.demo.demos.web.POJO.DTO.CreateUserDto;
import springone.com.demo.demos.web.POJO.DTO.LoginDTO;
import springone.com.demo.demos.web.POJO.VO.LoginVO;
import springone.com.demo.demos.web.POJO.VO.UserVo;
import springone.com.demo.demos.web.common.Result;
import springone.com.demo.demos.web.service.UserService;
import springone.com.demo.demos.web.POJO.entity.User;

@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 用户登录
     * @param loginDTO
     */
    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO){
        log.info("用户登录");
        return userService.login(loginDTO);
    }

    @PostMapping
    public Result saveUser(@RequestBody CreateUserDto createUserDto){
        log.info("新增员工");
        User user = new User();
        BeanUtils.copyProperties(createUserDto,user);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userService.save(user);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<UserVo> selectById(@PathVariable Long id){
        log.info("通过id查询信息");
        UserVo user =  userService.selectById(id);
        return new Result(200,user);
    }

    @DeleteMapping("/{id}")
    public Result<User> deleteById(@PathVariable Long id){
        log.info("通过id查询信息");
        userService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/send")
    public Result sendSmsCode(String phoneNumber){
        String message = userService.sendSmsCode(phoneNumber);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result updateUserInfo(@PathVariable int id ,@RequestBody CreateUserDto userDto){
        userDto.setId(id);
        userService.updateUserInfo(userDto);
        return Result.success();
    }

}
