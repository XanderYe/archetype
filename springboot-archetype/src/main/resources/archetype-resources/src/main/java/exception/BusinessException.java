#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exception;

import ${package}.enums.ErrorCodeEnum;
import org.slf4j.helpers.MessageFormatter;

/**
 *
 * @author XanderYe
 * @date 2018-11-05
 */
public class BusinessException extends RuntimeException {
    private int code;
    private String snapshot;

    public BusinessException(ErrorCodeEnum errorCode, String snapshotFormat, Object... argArray) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.snapshot = MessageFormatter.arrayFormat(snapshotFormat, argArray).getMessage();
    }

    public BusinessException(int code, String message, String snapshotFormat, Object... argArray) {
        super(message);
        this.code = code;
        this.snapshot = MessageFormatter.arrayFormat(snapshotFormat, argArray).getMessage();
    }

    public BusinessException(ErrorCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(String message) {
        super(message);
        this.code = 1;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getSnapshot() {
        return snapshot;
    }
}