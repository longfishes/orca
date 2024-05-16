package com.longfish.orca.service.impl;

import com.longfish.orca.pojo.entity.User;
import com.longfish.orca.mapper.UserMapper;
import com.longfish.orca.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author longfish
 * @since 2024-05-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
