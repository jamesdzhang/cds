package com.tuniu.car.basic.mongo.db;


import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author
 */
public interface CarOrderRequestRepository extends MongoRepository<CarOrderRequest<?>, String> {

}
