package br.com.jsonreadinsertmongo.config;

import br.com.jsonreadinsertmongo.domain.User;
import br.com.jsonreadinsertmongo.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class Instantiation {

    @Bean
    public static CommandLineRunner runner(UserService userService){
        return args -> {

            List<String> pathList = getFiles();

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

    private static List<String> getFiles() throws IOException {

        List<String> pathList = Files.walk(Paths.get("C:\\workspace\\jsonreadinsertmongo\\src\\main\\resources\\json"))
                .filter(Files::isRegularFile)
                .map(Path::toString)
                .collect(Collectors.toList());
        return pathList;
    }


}
