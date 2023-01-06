package br.com.jsonreadinsertmongo;


import br.com.jsonreadinsertmongo.domain.User;
import br.com.jsonreadinsertmongo.service.UserService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.bson.json.JsonObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@SpringBootApplication
public class JsonreadinsertmongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonreadinsertmongoApplication.class, args);
	}

	@Bean
	public static CommandLineRunner runner(UserService userService){
		return args -> {

			List<String> pathList = Files.walk(Paths.get("C:\\workspace\\jsonreadinsertmongo\\src\\main\\resources\\json"))
					.filter(Files::isRegularFile)
					.map(Path::toString)
					.collect(Collectors.toList());

			ObjectMapper mapper = new ObjectMapper();
			TypeReference<User> typeReference = new TypeReference<>() {};

			for (String input : pathList) {
				InputStream inputStream = new FileInputStream(input);
				try {
					User user = mapper.readValue(inputStream, typeReference);
					userService.save(user);
					System.out.println("User saved!");
				} catch (IOException e){
					System.out.println("Unable to save user: " + e.getMessage());
				}
			}
		};
	}
}
