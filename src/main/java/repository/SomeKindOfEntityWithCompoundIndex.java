package repository;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
@CompoundIndexes({
        @CompoundIndex(name = "TriggerHeader",
                def = "{'id': 1, 'eventTime': 1, 'type': 1, 'lastModified': 1, 'trigger': 1, 'origin': 1}")
})
@Document(collection = "collectionName")
public class SomeKindOfEntityWithCompoundIndex {
    String name;
}
