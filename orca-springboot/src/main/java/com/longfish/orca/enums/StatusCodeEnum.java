package com.longfish.orca.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {

    SUCCESS(200, "操作成功"),

    FAIL(0, "操作失败"),

    NOT_FOUND(404, "not found"),

    USERNAME_OR_PASSWORD_ERROR(40001, "用户名或密码错误"),

    NO_LOGIN(40002, "用户未登录"),

    CODE_ERROR(40003, "验证码错误"),

    USER_IS_NULL(40004, "用户名不能为空"),

    USER_EXIST(40005, "用户名已存在"),

    USER_NOT_EXIST(40006, "用户不存在"),

    FORMAT_ERROR(40007, "邮箱或手机格式不正确"),

    USERNAME_FORMAT_ERROR(40008, "用户名格式不正确"),

    DATE_FORMAT_ERROR(40009, "日期格式不正确"),

    PHONE_EXIST(40010, "手机号已经绑定"),

    EMAIL_EXIST(40011, "邮箱已经绑定"),

    AUTHORIZED(40100, "未认证"),

    PASSWORD_ERROR(40101, "密码错误"),

    FORBIDDEN(40300, "没有操作权限"),

    METHOD_NOT_ALLOWED(40500, "请求方法不允许"),

    SYSTEM_ERROR(50000, "系统异常"),

    VALID_ERROR(52000, "参数格式不正确");

    private final Integer code;

    private final String desc;

}
