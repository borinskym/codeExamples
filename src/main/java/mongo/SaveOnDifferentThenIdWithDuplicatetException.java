package mongo;

import com.mongodb.WriteResult;
import org.bson.types.ObjectId;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class SaveOnDifferentThenIdWithDuplicatetException {

    private MongoOperations mongoOperations;


    public TriggerRequest saveTriggerRequest(TriggerRequest entity) {

        WriteResult upsert = mongoOperations.upsert(query(where("originalTrigger").is(entity.getOriginalTrigger())),
                new Update()
                        .setOnInsert("eventId", entity.getEventId())
                        .setOnInsert("gameId", entity.getGameId())
                        .setOnInsert("triggerCategory", entity.getTriggerCategory())
                        .setOnInsert("sportType", entity.getSportType()),
                TriggerRequest.class
        );

        if(upsert.isUpdateOfExisting()){
            throw new DuplicateKeyException("duplicate found on originalTrigger :" + entity.getOriginalTrigger());
        }
        ObjectId objectId = (ObjectId)upsert.getUpsertedId();
        return mongoOperations.findById(objectId.toString(), TriggerRequest.class);

    }

}
