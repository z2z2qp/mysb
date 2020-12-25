package me.will.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * The type Application.
 */
@EnableAsync
@SpringBootApplication
public class Application {

    /**
     * spring boot 启动类
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");//完全异步模式
        SpringApplication.run(Application.class, args);
    }

}
