package com.pys.service.impl;

import com.pys.dao.UserDao;
import com.pys.entity.User;
import com.pys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User checkUser(String username, String password) {
       return userDao.findByUsernameAndPassword(username,password);
    }
}
