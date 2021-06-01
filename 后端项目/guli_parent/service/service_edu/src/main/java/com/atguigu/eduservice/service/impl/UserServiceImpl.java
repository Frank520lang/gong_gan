package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.User;
import com.atguigu.eduservice.mapper.UserMapper;
import com.atguigu.eduservice.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
