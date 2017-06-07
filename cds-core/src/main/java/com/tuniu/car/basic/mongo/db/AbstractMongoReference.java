package com.tuniu.car.basic.mongo.db;

import org.springframework.data.annotation.Id;

/**
 * Created by  on 14-5-27.
 */
public abstract class AbstractMongoReference implements MongoReference {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
