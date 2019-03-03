package config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    private MongoClientURI mongoClientUri;
    private static final int MAX_CONNECTION_IDLE_TIME = 30000;

    @Autowired
    public void setMongoClientUri(MongoClientURI mongoClientUri) {
        this.mongoClientUri = mongoClientUri;
    }

    @Autowired
    @Bean
    public MongoClientURI mongoClientURI(@Value("${code-example.mongo.uri}") String uri) {
        MongoClientOptions.Builder mongoClientOptionsBuilder =
                MongoClientOptions.builder()
                        .maxConnectionIdleTime(MAX_CONNECTION_IDLE_TIME);
        return new MongoClientURI(uri, mongoClientOptionsBuilder);
    }

    @Override
    @Bean
    public Mongo mongo() {
        return new MongoClient(mongoClientUri);
    }

    @Override
    protected String getDatabaseName() {
        return mongoClientUri.getDatabase();
    }
}