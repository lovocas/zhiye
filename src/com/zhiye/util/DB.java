package com.zhiye.util;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public class DB {
    public static final Mongo mongo = MongoDB.instance().getMongo();
    public static final Morphia morphia = MongoDB.instance().getMorphia();
    public static final Datastore ds = MongoDB.instance().getDatastore();
    
    private DB() throws Exception {
        throw new Exception("你用的是反射吗");
    }
}
