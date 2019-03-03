package mongo;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class QueryNestedObjects {
    private MongoOperations mongoOperations;


    public void nested(TriggerRequest entity){
        mongoOperations.upsert(query(
                where("originalTrigger_.header_.id").is(entity.getOriginalTrigger().getHeader().getId())
                        .and("originalTrigger_.header_.type").is(entity.getOriginalTrigger().getHeader().getType())
                        .and("originalTrigger_.header_.eventTime").is(entity.getOriginalTrigger().getHeader().getEventTime()))
                ,
                new Update()
                        .setOnInsert("eventId", entity.getEventId())
                        .setOnInsert("gameId", entity.getGameId())
                        .setOnInsert("triggerCategory", entity.getTriggerCategory())
                        .setOnInsert("sportType", entity.getSportType())
                        .setOnInsert("originalTrigger", entity.getOriginalTrigger()),
                TriggerRequest.class
        );
    }
}
