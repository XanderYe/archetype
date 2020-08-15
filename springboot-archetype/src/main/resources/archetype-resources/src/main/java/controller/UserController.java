#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import ${package}.base.ResultBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("login")
    public ResultBean login(String username, String password) {
        return new ResultBean("login");
    }

    @PostMapping("logout")
    public ResultBean logout() {
        return new ResultBean("logout");
    }
}
