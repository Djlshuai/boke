package com.example.bokedesign.service.impl;

import com.example.bokedesign.entity.User;
import com.example.bokedesign.mapper.UserMapper;
import com.example.bokedesign.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author boke
 * @since 2022-10-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
