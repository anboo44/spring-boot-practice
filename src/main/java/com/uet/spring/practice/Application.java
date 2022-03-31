package com.uet.spring.practice;

import com.uet.spring.practice.condition.IsLocalEnviCondition;
import com.uet.spring.practice.condition.IsProductionEnviCondition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAsync
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Conditional(IsLocalEnviCondition.class)
	@Bean // Create Bean to follow class condition
	void helloBean() {
		System.out.println("=====[ Welcome to SpringBoot. Here is local envi ]===========");
	}

	@Conditional(IsProductionEnviCondition.class)
	@Bean
	void helloProduction() {
		System.out.println("=====[ Welcome to SpringBoot. Here is production envi ]===========");
	}

	@Bean
	LinkBean linkedBean1() {
		System.out.println("==============[ Linked Bean 1 is created ]========================");
		return new LinkBean();
	}

	@ConditionalOnBean(LinkBean.class)
	@Bean
	void linkedBean2() {
		System.out.println("==============[ Linked Bean 2 is created to follow LinkedBean1 ]========================");
	}

	@Bean
	@ConditionalOnProperty(value = "bean.property-bean.enabled", havingValue = "true", matchIfMissing = false)
	void propertyBean() {
		System.out.println("==============[ Property Bean is created ]=======================");
	}

	@Bean
	@ConditionalOnResource(resources = "/application.properties")
	void existFileResourceBean() {
		System.out.println("==============[ Having resource file ]=======================");
	}
}

class LinkBean {}
