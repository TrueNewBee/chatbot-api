package vip.chentianxiang.chatbot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @PROJECT_NAME:
 * @USER: TrueNewBee
 * @DATE: 2023/2/25 15:04
 * @DESCRIPTION: 启动入口
 */
@SpringBootApplication
@EnableScheduling
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
