package com.longfish.orca.controller.m;


import com.longfish.orca.annotation.AccessLimit;
import com.longfish.orca.annotation.NoLogin;
import com.longfish.orca.context.BaseContext;
import com.longfish.orca.context.UploadStrategyContext;
import com.longfish.orca.enums.StatusCodeEnum;
import com.longfish.orca.pojo.Result;
import com.longfish.orca.pojo.dto.*;
import com.longfish.orca.pojo.vo.MobileLoginVO;
import com.longfish.orca.pojo.vo.UserVO;
import com.longfish.orca.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.longfish.orca.constant.CommonConstant.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author longfish
 * @since 2024-05-16
 */
@RestController(value = "mUserController")
@RequestMapping("/m/user")
@Tag(name = "用户相关")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Operation(summary = "用户名唯一性检验")
    @GetMapping("/uniqueCheck")
    @NoLogin
    public Result<?> uniqueCheck(String username) {
        return userService.usernameUniqueCheck(username) ? Result.error(StatusCodeEnum.USER_EXIST) : Result.success();
    }

    @Operation(summary = "条件登录密码")
    @PostMapping("/lambdaLogin")
    @NoLogin
    public Result<MobileLoginVO> login(@RequestBody LambdaLoginDTO lambdaLoginDTO) {
        return Result.success(MobileLoginVO.builder().uid(userService.login4Uid(lambdaLoginDTO)).build());
    }

    @Operation(summary = "条件登录验证码")
    @PostMapping("/lambdaCodeLogin")
    @NoLogin
    public Result<MobileLoginVO> loginByCode(@RequestBody LambdaCodeLoginDTO lambdaCodeLoginDTO) {
        return Result.success(MobileLoginVO.builder().uid(userService.codeLogin4Uid(lambdaCodeLoginDTO)).build());
    }

    @Operation(summary = "退出登录", parameters = {@Parameter(
            name = ID_NAME, required = true,
            in = ParameterIn.HEADER,
            description = MOBILE_HEADER_ADVICE,
            example = MOBILE_HEADER_VAR)})
    @GetMapping("/logout")
    public Result<?> logout() {
        BaseContext.removeCurrentId();
        return Result.success();
    }

    @Operation(summary = "发送验证码")
    @GetMapping("/code")
    @AccessLimit(seconds = 5, maxCount = 1)
    @NoLogin
    public Result<?> code(String username) {
        userService.code(username);
        return Result.success();
    }

    @Operation(summary = "条件注册")
    @PostMapping("/lambdaRegister")
    @NoLogin
    public Result<?> lambdaRegister(@RequestBody RegDTO regDTO) {
        userService.register(regDTO);
        return Result.success();
    }

    @Operation(summary = "我的信息", parameters = {@Parameter(
            name = ID_NAME, required = true,
            in = ParameterIn.HEADER,
            description = MOBILE_HEADER_ADVICE,
            example = MOBILE_HEADER_VAR)})
    @GetMapping("/me")
    public Result<UserVO> me() {
        return Result.success(userService.me());
    }

    @Operation(summary = "忘记密码")
    @PostMapping("/forgot")
    @NoLogin
    public Result<?> forgot(@RequestBody ForgotDTO forgotDTO) {
        userService.forgot(forgotDTO);
        return Result.success();
    }

    @Operation(summary = "修改用户信息", parameters = {@Parameter(
            name = ID_NAME, required = true,
            in = ParameterIn.HEADER,
            description = MOBILE_HEADER_ADVICE,
            example = MOBILE_HEADER_VAR)})
    @PutMapping("/info")
    public Result<?> update(@RequestBody UserInfoDTO userInfoDTO) {
        userService.updateInfo(userInfoDTO);
        return Result.success();
    }

    @Operation(summary = "修改密码", parameters = {@Parameter(
            name = ID_NAME, required = true,
            in = ParameterIn.HEADER,
            description = MOBILE_HEADER_ADVICE,
            example = MOBILE_HEADER_VAR)})
    @PutMapping("/password")
    public Result<?> editPassword(@RequestBody PasswordEditDTO passwordEditDTO) {
        userService.editPassword(passwordEditDTO);
        return Result.success();
    }

    @Operation(summary = "修改用户名", parameters = {@Parameter(
            name = ID_NAME, required = true,
            in = ParameterIn.HEADER,
            description = MOBILE_HEADER_ADVICE,
            example = MOBILE_HEADER_VAR)})
    @PutMapping("/username")
    public Result<?> editUsername(@RequestBody UsernameDTO usernameDTO) {
        userService.editUsername(usernameDTO);
        return Result.success();
    }

    @Operation(summary = "绑定手机", parameters = {@Parameter(
            name = ID_NAME, required = true,
            in = ParameterIn.HEADER,
            description = MOBILE_HEADER_ADVICE,
            example = MOBILE_HEADER_VAR)})
    @PutMapping("/phone")
    public Result<?> bindPhone(@RequestBody PhoneBindDTO phoneBindDTO) {
        userService.bindPhone(phoneBindDTO);
        return Result.success();
    }

    @Operation(summary = "绑定邮箱", parameters = {@Parameter(
            name = ID_NAME, required = true,
            in = ParameterIn.HEADER,
            description = MOBILE_HEADER_ADVICE,
            example = MOBILE_HEADER_VAR)})
    @PutMapping("/email")
    public Result<?> bindEmail(@RequestBody EmailBindDTO emailBindDTO) {
        userService.bindEmail(emailBindDTO);
        return Result.success();
    }

}
