package testRestWithQueryParams;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.SomeKindOfEntityWithCompoundIndex;
import repository.SomeKindOfRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoRepositoryContext.class})

public class IntegrationTestWIthMongoAndInjectVariable {

    @Autowired
    private SomeKindOfRepository someKindOfRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Before
    public void setUp(){
        mongoTemplate.dropCollection("some collection");
    }

    @Test
    public void someTestThatUsesRepository() {
        // now you can use all the pre-configured functioned of spring-mongodb
        someKindOfRepository.save(new SomeKindOfEntityWithCompoundIndex());
        someKindOfRepository.delete("ASD");
        someKindOfRepository.findAll();

        //this is the addition, spring knows how to add this code
        someKindOfRepository.additionalFunctionToRepository(new SomeKindOfEntityWithCompoundIndex());
    }
}
