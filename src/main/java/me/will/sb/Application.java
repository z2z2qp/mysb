package me.will.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.dev33.satoken.spring.SaTokenSetup;
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
		SpringApplication.run(Application.class, args);
	}

}
