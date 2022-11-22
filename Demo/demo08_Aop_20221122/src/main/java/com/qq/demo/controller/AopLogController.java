package com.qq.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longzhonghua
 * @data 2019/02/03 09:50
 */
@Slf4j
@RestController
public class AopLogController {
    @GetMapping("/aoptest")
    public String aVoid() {
        log.info("com/qq/demo/aop");
        return "hello com.qq.demo.controller.aop test";
    }
}
