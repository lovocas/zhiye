package com.zhiye.util;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;


/**
 * MongoDB providing the database connection.
 */
public class MongoDB {
    public static final String DB_NAME = "zhiye";
    
    
    private static final MongoDB INSTANCE = new MongoDB();
    private final Datastore datastore;
    private final Morphia morphia;
    private final Mongo mongo;
    private MongoDB() {
        try {
            mongo = new Mongo("localhost", 27017);
            morphia = new Morphia();
            datastore = morphia.createDatastore(mongo, DB_NAME);
            datastore.ensureCaps();
            datastore.ensureIndexes();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing MongoDB", e);
        }
    }

    public static MongoDB instance() {
        return INSTANCE;
    }
    public Datastore getDatastore() {
        return datastore;
    }
    public Morphia getMorphia() {
        return morphia;
    }
    public Mongo getMongo() {
        return mongo;
    }
}
