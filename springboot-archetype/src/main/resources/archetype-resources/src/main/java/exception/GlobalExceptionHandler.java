#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exception;

import ${package}.base.ResultBean;
import ${package}.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
/**
 * @author XanderYe
 * @date 2018-11-05
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ResultBean<>(status.value(), status.getReasonPhrase()), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ResultBean<>(ErrorCodeEnum.INTERNAL_ERROR), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleBusinessException(BusinessException e) {
        return new ResponseEntity<>(new ResultBean<>(e.getCode(), e.getMessage()), HttpStatus.OK);
    }
}
