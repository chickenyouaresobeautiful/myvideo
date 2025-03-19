package com.daiweij.myvedio.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daiweij.myvedio.common.exception.CustomException;
import com.daiweij.myvedio.modules.sys.entity.UsersEntity;
import com.daiweij.myvedio.modules.sys.service.UsersService;
import com.daiweij.myvedio.modules.sys.mapper.UsersMapper;
import org.springframework.stereotype.Service;

/**
 * @author 26385
 * @description 针对表【users】的数据库操作Service实现
 * @createDate 2024-08-11 19:46:53
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersEntity>
        implements UsersService {

    @Override
    public void updateUserAvatar(int userId, String fileName) {
        UsersEntity optionalUser = getById(userId);
        if (optionalUser == null) {
            throw new CustomException("用户不存在", 4004);
        }
        optionalUser.setAvatar(fileName);
        updateById(optionalUser);
    }
}




