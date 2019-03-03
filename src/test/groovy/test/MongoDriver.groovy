package test

import com.mongodb.MongoClient
import org.bson.Document

class MongoDriver {
    def dbserver = System.getenv("MONGO_HOST") ?:"localhost"
    def mongo = new MongoClient(dbserver).getDatabase("moment-moderator-it")

    def dropDB() {
        mongo.getCollection("scores").deleteMany(new Document());
    }
}