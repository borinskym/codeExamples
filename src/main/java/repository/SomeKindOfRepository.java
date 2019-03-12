package repository;

import org.springframework.data.mongodb.repository.MongoRepository;
/*
if you want other things than regular you have to extends this interface with exact name :
 SomeKindOfRepositoryImpl !!!!! ,
 and also extend an interface with this custom function
 */

public interface SomeKindOfRepository extends MongoRepository<SomeKindOfEntityWithCompoundIndex, String>, CustomRepository {
}
