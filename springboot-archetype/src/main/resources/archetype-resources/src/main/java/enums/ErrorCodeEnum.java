#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.enums;

import lombok.Getter;

/**
 *
 * @author XanderYe
 * @date 2018-11-05
 */
@Getter
public enum ErrorCodeEnum {
    /*错误码*/
    ACCOUNT_OR_PASSWORD_ERROR(10101, "账号或密码错误！"),
    ACCOUNT_NOTEXIST(10102, "账号不存在！"),
    ACCOUNT_EXPIRED(10103, "账号已过期！"),
    ACCOUNT_AUTH_ERROR(10104, "账号认证异常！"),
    ACCOUNT_DISABLED(10105, "账号被禁用！"),
    ACCOUNT_EXIST(10106, "用户名已存在！"),
    UNSAFE_PASSWORD(10201, "密码不安全！"),
    PARAMETER_ERROR(10202, "参数错误！"),
    PARAMETER_EMPTY(10203, "参数不为空！"),
    CAPTCHA_ERROR(10204, "验证码错误！"),
    NICKNAME_TOOLONG(10205, "昵称过长！"),
    EMAIL_ERROR(10206, "邮箱格式错误！");
    private int code;
    private String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}