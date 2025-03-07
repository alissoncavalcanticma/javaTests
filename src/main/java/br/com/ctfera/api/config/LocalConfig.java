package br.com.ctfera.api.config;

import br.com.ctfera.api.domain.User;
import br.com.ctfera.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public CommandLineRunner startDB(UserRepository userRepository){
        return args-> {
            User user1 = new User(null, "Alisson", "alisson@alisson.com", "321");
            User user2 = new User(null, "Christian", "ctfera@gmail.com", "123");

            userRepository.saveAll(List.of(user1, user2));
        };
    }
}
