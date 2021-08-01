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
    PAGE_NOT_FOUND(404, "资源不存在"),
    INTERNAL_ERROR(500, "服务器内部错误"),
    PARAMETER_EMPTY(10001, "参数不为空"),
    PARAMETER_ERROR(10002, "参数错误"),
    DATA_ERROR(10003, "获取异常"),
    AUTHENTICATION_EXCEPTION(20001, "认证异常！"),
    ACCOUNT_OR_PASSWORD_ERROR(20002, "账号或密码错误！"),
    ACCOUNT_EXIST(20003, "用户名已存在！"),
    UNSAFE_PASSWORD(20004, "密码不安全！"),
    ACCOUNT_PASSWORD_ERROR(20005, "密码错误！"),
    ACCOUNT_BANNED(20006, "账户被禁用"),
    PERMISSION_DENIED(20007, "权限不足"),
    CAPTCHA_ERROR(30001, "验证码错误！");
    private int code;
    private String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据code获取异常
     * @param code
     * @return cn.xanderye.enums.ErrorCodeEnum
     * @author XanderYe
     * @date 2021/7/31
     */
    public static ErrorCodeEnum getErrorByCode(Integer code) {
        if (code != null) {
            for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
                if (code == errorCodeEnum.getCode()) {
                    return errorCodeEnum;
                }
            }
        }
        return ErrorCodeEnum.INTERNAL_ERROR;
    }
}
