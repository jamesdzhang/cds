/**
 * Copyright (C) 2006-2014 Tuniu All rights reserved
 * Author：James
 * Date：2014年7月8日
 * Description: 
 */

package com.tuniu.car.basic.mongo.db.typehandler;


import com.tuniu.car.basic.mongo.db.CarOrderResponse;
import com.tuniu.car.basic.mongo.db.CarOrderResponseRepository;
import com.tuniu.car.basic.mongo.db.factory.MongoRepositoryFactory;
import org.apache.ibatis.type.MappedTypes;


/**
 * Description :
 * 
 */
@MappedTypes(CarOrderResponse.class)
public class OrderResponseTypeHandler extends AbstractMongoReferenceHandler<CarOrderResponse<?>> {


    private Class type;

    public OrderResponseTypeHandler(Class type) {
        if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
    }

    private CarOrderResponseRepository orderResponseRepository = (CarOrderResponseRepository) MongoRepositoryFactory
            .getInstance("carOrderResponseRepository");

    @Override
    protected CarOrderResponse<?> findOne(String id) {
        return orderResponseRepository.findById(id).get();
    }

    @Override
    protected String save(CarOrderResponse<?> orderRequest) {
        orderResponseRepository.save(orderRequest);
        return orderRequest.getId();
    }

}
