package com.github.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peach
 * @since 2020/12/10 17:26
 */
@RestController
@RequestMapping("/order")
public class OrderController {


    @RequestMapping("/get")
    public String get(){
        int i = 1/ 0;
        return "ok";
    }
}