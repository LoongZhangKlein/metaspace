package com.shiro.contorller;

import com.shiro.entity.User;
import com.shiro.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author loongzhang
 * @Description DOING
 * @date 2023-01-30-14:37
 */
@Controller
public class MyController {
    @Resource
    UserMapper userMapper;
    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "shrio");
        return "index";

    }

    @RequestMapping("/user/update")
    public String toUpdate() {
//        model.addAttribute("msg","shrio");
        return "user/update";

    }

    @RequestMapping("/user/add")
    public String toAdd() {
//        model.addAttribute("msg","shrio");
        return "user/add";

    }

    @RequestMapping("/user/toLogin")
    public String toLogin() {

//        model.addAttribute("msg","shrio");
        return "/toLogin";

    }

    @RequestMapping("/login")
    public String login(String userName, String passWord, Model model) {


        // 获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        /**
         * 执行登录方法
         * 该登录方法执行时发生异常则登录验证失败,反之成功
         * 同时可以根据异常信息进行捕获 可以详细的捕获用户名错误或者是密码错误
         */
        try {
            subject.login(token);
            return "/index";
        } catch (UnknownAccountException unknownAccountException) {
            model.addAttribute("msg","用户名错误");
           return "/toLogin";
        } catch (IncorrectCredentialsException incorrectCredentialsException) {
            model.addAttribute("msg","密码错误");
            return "/toLogin";
        }
    }
}
