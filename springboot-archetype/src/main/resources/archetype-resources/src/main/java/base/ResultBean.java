#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.base;

import ${package}.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * @author XanderYe
 * @date 2019-01-10
 */
@Data
@Accessors(chain = true)
public class ResultBean<R> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String msg = "success";

    private int code = 0;
    private R data;

    public ResultBean() {
    }

    public ResultBean(int code, String message, R data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public ResultBean(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    public ResultBean(R data) {
        this.data = data;
    }

    public ResultBean(R data, String message) {
        this.data = data;
        this.msg = message;
    }

    /**
     * 成功
     * @return ${package}.base.ResultBean
     * @author XanderYe
     * @date 2019/9/16
     */
    public static <R> ResultBean<R> success(R data) {
        return new ResultBean<R>().setData(data);
    }

    /**
     * 失败
     * @param msg
     * @return ${package}.base.ResultBean
     * @author XanderYe
     * @date 2019/9/16
     */
    public static <R> ResultBean<R> error(String message) {
        return new ResultBean<>(1, message);
    }

    /**
     * 失败
     * @param errorCode
     * @return ${package}.base.ResultBean
     * @author XanderYe
     * @date 2019/9/16
     */
    public static <R> ResultBean<R> error(ErrorCodeEnum errorCode) {
        return new ResultBean<>(errorCode.getCode(), errorCode.getMessage());
    }
}