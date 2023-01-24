package br.com.jsonreadinsertmongo.config;

import br.com.jsonreadinsertmongo.domain.User;
import br.com.jsonreadinsertmongo.repository.UserRepository;
import br.com.jsonreadinsertmongo.service.UserService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public JsonItemReader<User> reader(){

        JsonItemReader<User> itemReader = new JsonItemReader<>();
        itemReader.setJsonObjectReader(new JacksonJsonObjectReader<>(User.class));
        itemReader.setResource(new ClassPathResource("/json/users.json"));
        itemReader.setName("UsersItemReader");
        itemReader.setStrict(false);

        return itemReader;
    }

    @Bean
    public UserItemProcessor processor(){
        return new UserItemProcessor();
    }

    @Bean
    public RepositoryItemWriter<User> writer(){
        RepositoryItemWriter<User> writer = new RepositoryItemWriter<>();
        writer.setRepository(userRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("json-step").<User, User>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job runJob(){
        return jobBuilderFactory.get("importUsers")
                .flow(step1()).end().build();
    }
}
