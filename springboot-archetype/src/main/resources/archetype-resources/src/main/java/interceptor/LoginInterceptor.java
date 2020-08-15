#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.interceptor;

import ${package}.base.RequestContextHolder;
import ${package}.entity.User;
import ${package}.enums.ErrorCodeEnum;
import ${package}.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Xander on 2018-11-05.
 */
@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String remoteAddr = request.getRemoteAddr();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        //不拦截OPTION请求
        if (method.equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        String token = request.getHeader("token");
        log.info("remoteAddr={},  method={}, uri={}, token={}", remoteAddr, method, uri, token);

        if (StringUtils.isEmpty(token)) {
            throw new BusinessException(ErrorCodeEnum.ACCOUNT_AUTH_ERROR);
        }
        // 鉴权
        User user = new User();
        // 保存user
        RequestContextHolder.set(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        RequestContextHolder.reset();
    }
}