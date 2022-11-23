package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动
 *
 * @author qq
 * @date:2018/12/27
 */
@SpringBootApplication
public class PreventApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(PreventApplication.class, args);
        } catch (Exception e) {
            System.out.printf(e.toString());
        }

    }

}
