package hw2.booklist.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataLoader {

    @Autowired
    private UserRepository userRepository;

    @Bean
    CommandLineRunner initUsers() {
        return args -> {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            User user = new User(
                    "user",
                    passwordEncoder.encode("password"),
                    "user@gmail.com",
                    "USER");

            User admin = new User(
                    "admin",
                    passwordEncoder.encode("admin"),
                    "admin@gmail.com",
                    "ADMIN");

            userRepository.save(user);
            userRepository.save(admin);
        };
    }
}