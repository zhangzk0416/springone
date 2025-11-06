package springone.com.demo.demos.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springone.com.demo.demos.web.mapper.UserMapper;
import springone.com.demo.demos.web.service.UserService;
import springone.com.demo.demos.web.dao.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(User loginVO) {
        userMapper.insert(loginVO);
    }
}
