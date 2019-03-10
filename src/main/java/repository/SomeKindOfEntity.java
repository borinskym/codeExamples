package repository;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "collectionName")
public class SomeKindOfEntity {
    String name;
}
