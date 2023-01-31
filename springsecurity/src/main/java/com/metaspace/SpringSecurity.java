package com.metaspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author loongzhang
 * @Description DOING
 * @date ${DATE}-${TIME}
 */
@SpringBootApplication
public class SpringSecurity {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity.class,args);
        System.out.println("SpringSecurity  启动成功");
    }
}