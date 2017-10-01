package cn.lewis.all_in.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    private Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "login")
    public String login(final HttpServletRequest httpServletRequest,
                        final HttpServletResponse httpServletResponse,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password) {
        LOG.info("Login.login username:{}, password:{}", username, password);
        boolean isExist = true;
        if (isExist) {
            return "redirect:/index.html";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("main")
    public String main(Model model) {
        LOG.info("LoginController.main");
        model.addAttribute("name", "longman");
        return "main";
    }

}
