package com.metaspace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author loongzhang
 * @Description DOING
 * @date 2023-01-31-15:41
 */
@RestController
public class IndexController {
    @RequestMapping("/index")
    public String index() {
        return "hello springSecurity";
    }
}
