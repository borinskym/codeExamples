package RetryMechanism;

import com.github.rholder.retry.*;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class RetryExample {

    MongoTemplate mongoTemplate;

    public void retry(){
        try {
            Document document = retryBuilder().call(() -> mongoTemplate.findOne(
                    query(where("notificationId").is("asd")),
                    Document.class, "REPO_NAME"));
        } catch (ExecutionException | RetryException e) {
            //this is the exception when non found
            e.printStackTrace();
        }
    }

    private Retryer<Document> retryBuilder() {
        return RetryerBuilder.<Document>newBuilder()
                .retryIfResult(Objects::isNull)
                .withWaitStrategy(WaitStrategies.fixedWait(20, TimeUnit.MILLISECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(10))
                .build();
    }


}
