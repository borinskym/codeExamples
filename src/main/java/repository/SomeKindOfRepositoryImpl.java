package repository;

import org.springframework.data.mongodb.core.MongoTemplate;

public class SomeKindOfRepositoryImpl implements CustomRepository {

    private MongoTemplate mongoTemplate;

    public SomeKindOfRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /*
        this is the class needed for adding functionality
         */
    @Override
    public void additionalFunctionToRepository(SomeKindOfEntityWithCompoundIndex entity) {

    }
}
