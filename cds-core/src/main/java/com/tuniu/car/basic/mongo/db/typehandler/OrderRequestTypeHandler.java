/**
 * Copyright (C) 2006-2014 Tuniu All rights reserved
 * Author：James
 * Date：2014年7月8日
 * Description: 
 */

package com.tuniu.car.basic.mongo.db.typehandler;


import com.tuniu.car.basic.mongo.db.CarOrderRequest;
import com.tuniu.car.basic.mongo.db.CarOrderRequestRepository;
import com.tuniu.car.basic.mongo.db.factory.MongoRepositoryFactory;
import org.apache.ibatis.type.MappedTypes;


/**
 * Description :
 * 
 */
@MappedTypes(CarOrderRequest.class)
public class OrderRequestTypeHandler extends AbstractMongoReferenceHandler<CarOrderRequest<?>> {


    private Class type;

    public OrderRequestTypeHandler(Class type) {
        if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
    }

    private CarOrderRequestRepository orderRequestRepository = (CarOrderRequestRepository) MongoRepositoryFactory
            .getInstance("carOrderRequestRepository");

    @Override
    protected CarOrderRequest<?> findOne(String id) {
        return orderRequestRepository.findById(id).get();
    }

    @Override
    protected String save(CarOrderRequest<?> orderRequest) {
        orderRequestRepository.save(orderRequest);
        return orderRequest.getId();
    }

}
