package com.shiro.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author loongzhang
 * @Description DOING
 * @date 2023-01-30-14:37
 */
@Controller
public class MyController {
    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","shrio");
        return "index";

    }
    @RequestMapping("/user/update")
    public String toUpdate(){
//        model.addAttribute("msg","shrio");
        return "user/update";

    }
    @RequestMapping("/user/add")
    public String toAdd(){
//        model.addAttribute("msg","shrio");
        return "user/add";

    }
    @RequestMapping("/user/toLogin")
    public String toLogin(){
        System.out.println("调用了");
//        model.addAttribute("msg","shrio");
        return "/toLogin";

    }
}
