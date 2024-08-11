package com.example.myvedio.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myvedio.entity.UsersEntity;
import com.example.myvedio.service.UsersService;
import com.example.myvedio.mapper.UsersMapper;
import org.springframework.stereotype.Service;

/**
 * @author 26385
 * @description 针对表【users】的数据库操作Service实现
 * @createDate 2024-08-11 19:46:53
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersEntity>
        implements UsersService {

}




