package cn.lewis.all_in.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("user")
public class LoginController {
    private Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse,
                        @RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password) {
        LOG.info("Login.login username:{}, password:{}", username, password);
        boolean isExist = false;

        if (isExist) {
            return "redirect:/main";
        } else {
            return "redirect:/";
        }
    }
}
