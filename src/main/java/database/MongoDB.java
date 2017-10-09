package database;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import data.VO.MonthlyStockQuote;
import lombok.extern.log4j.Log4j;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import service.Database;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static data.utils.Constants.*;

/**
 * Created by Ameer on 10/5/17.
 */
@Log4j
public class MongoDB implements Database{

    private final String CONFIGURATION_FILE = "config/application.properties";
    private String mongoURI = "";
    private Jongo jongo;

    public MongoDB() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        log.debug("Loading application file " + CONFIGURATION_FILE);
        try {
            InputStream resourceStream = loader.getResourceAsStream(CONFIGURATION_FILE);
            props.load(resourceStream);
            mongoURI = (String) props.get("mongo.db.uri");
        } catch (IOException e) {
            log.error("Error loading configuration file " + e.toString());
        }
        setup();
    }

    public void setJongo(Jongo jongo) {
        this.jongo = jongo;
    }

    public void setup() {
        MongoClientURI uri = new MongoClientURI(mongoURI);
        MongoClient mongoClient = new MongoClient(uri);
        DB db = mongoClient.getDB(STOCK_QUOTES_DATABASE);
        setJongo(new Jongo(db));
    }

//    public boolean checkMonthlyStockQuoteExists(String ticker){
//        return getMonthlyStockQuote(ticker) != null;
//    }

    public void addMonthlyStockQuote(MonthlyStockQuote msq){
        log.debug("Adding MonthlyStockQuote to Mongo");
        MongoCollection collection = jongo.getCollection(MONTHLY_COLLECTION);
        collection.insert(msq);
    }

    public MonthlyStockQuote getMonthlyStockQuote(String ticker){
        log.debug("Fetching Monthly Stock Quote for ticker " + ticker + " from MongoDB");
        MongoCollection collection = jongo.getCollection(MONTHLY_COLLECTION);
        return collection.findOne("{ticker : #}",ticker).as(MonthlyStockQuote.class);
    }

    public static void main(String[] args) {
            new MongoDB();

        MongoClientURI uri = new MongoClientURI("");
        MongoClient mongoClient = new MongoClient(uri);
        DB db = mongoClient.getDB("testaaa");
//        db.dropDatabase();
//        DB dbAnother = mongoClient.getDB("test2");
//        dbAnother.dropDatabase();
//        MongoDatabase db2 = mongoClient.getDatabase("testaaa");
//        Jongo jongo = new Jongo(db);
//        db2.createCollection("sampleCollection");
//        MongoCollection sample = jongo.getCollection("sampleCollection");
//        sample.insert("{name: 'Joe', age: 18}");
//        System.out.println(sample.find("{name: 'Joe'}").toString());
//        System.out.println(sample.count());
////        MongoCollection<org.bson.Document> collection = db.getCollection("sampleCollection");
//        mongoClient.getDatabase("test2");
//        MongoIterable<String> dbs = mongoClient.listDatabaseNames();
//        for(String d : dbs){
//            System.out.println(d);
//        }
        mongoClient.getDatabaseNames().forEach(System.out::println);
    }
}
