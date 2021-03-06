package com.bamboo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@ServletComponentScan
@MapperScan({"com.bamboo.*.dao", "com.bamboo.*.mapper"})
@SpringBootApplication
public class BambooApplication {
	public static void main(String[] args) {
		SpringApplication.run(BambooApplication.class, args);
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container -> {
			ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
			container.addErrorPages(error401Page, error404Page, error500Page);
			container.setSessionTimeout(1800);// 单位为S
		});
	}
	
	/**
	 * 启动chat服务
	 * @return
	 */
//	@Bean
//	public ChatServer startChat() {
//		ChatServer chatServer = new ChatServer(9527);
//		chatServer.start();
//		return chatServer;
//	}

}
