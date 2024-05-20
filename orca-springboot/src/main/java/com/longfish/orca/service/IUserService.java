package com.longfish.orca.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfish.orca.pojo.dto.*;
import com.longfish.orca.pojo.entity.User;
import com.longfish.orca.pojo.vo.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author longfish
 * @since 2024-05-16
 */
public interface IUserService extends IService<User> {

    Boolean usernameUniqueCheck(String username);

    String login(LambdaLoginDTO lambdaLoginDTO);

    String codeLogin(LambdaCodeLoginDTO lambdaCodeLoginDTO);

    String login4Uid(LambdaLoginDTO lambdaLoginDTO);

    String codeLogin4Uid(LambdaCodeLoginDTO lambdaCodeLoginDTO);

    void code(String username);

    UserVO me();

    void register(RegDTO regDTO);

    void forgot(ForgotDTO forgotDTO);

    void updateInfo(UserInfoDTO userInfoDTO);

    void editPassword(PasswordEditDTO passwordEditDTO);

    void editUsername(UsernameDTO usernameDTO);

    void bindPhone(PhoneBindDTO phoneBindDTO);

    void bindEmail(EmailBindDTO emailBindDTO);
}
