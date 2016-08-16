package com.demo.concept;

import com.demo.concept.config.MainConfig;
import com.demo.concept.config.PersistenceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource({ "application-context.xml" })
public class Application
{

	public static void main(final String[] args)
	{
		SpringApplication.run(new Object[] { Application.class, MainConfig.class, PersistenceConfig.class }, args);
	}

}
