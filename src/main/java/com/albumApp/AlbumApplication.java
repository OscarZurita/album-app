package com.albumApp;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class AlbumApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx){
			return args -> {System.out.println("testing");};
	}
	@Component
	public class DataLoader implements CommandLineRunner {
    	private final DataSource dataSource;

    	public DataLoader(DataSource dataSource) {
        	this.dataSource = dataSource;
   		}

    	@Override
    	public void run(String... args) throws SQLException {
        	try (Connection connection = dataSource.getConnection()) {
            	ScriptUtils.executeSqlScript(connection, new ClassPathResource("data.sql"));
            	System.out.println("Data.sql executed successfully");
        	}
    	}
	}

}
