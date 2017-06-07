package com.tuniu.car.basic.mongo.db.typehandler;


import com.tuniu.car.basic.mongo.db.MongoReference;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by
 */
public abstract class AbstractMongoReferenceHandler<T  extends MongoReference> extends BaseTypeHandler<T> {

    protected abstract String save(T parameter);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        if (parameter.getId() != null) {
            ps.setString(i, parameter.getId());
        } else {
            ps.setString(i, save(parameter));
        }        
    }

    protected abstract T findOne(String id);

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String id = rs.getString(columnName);
        return id == null ? null : findOne(id);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String id = rs.getString(columnIndex);
        return id == null ? null : findOne(id);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String id = cs.getString(columnIndex);
        return id == null ? null : findOne(id);
    }
}
