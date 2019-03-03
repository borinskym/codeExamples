package testRestWithQueryParams;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration()
@EnableMongoRepositories({"repository"})
public class MongoRepositoryContext {
    private static final String MONGO_HOST =
            (System.getenv("MONGO_HOST") == null) ? "localhost" : System.getenv("MONGO_HOST");
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(new MongoClient(MONGO_HOST), "uer-notifier-it");
    }
}
