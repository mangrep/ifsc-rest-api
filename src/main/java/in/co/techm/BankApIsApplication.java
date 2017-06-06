package in.co.techm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BankApIsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApIsApplication.class, args);
    }
}
