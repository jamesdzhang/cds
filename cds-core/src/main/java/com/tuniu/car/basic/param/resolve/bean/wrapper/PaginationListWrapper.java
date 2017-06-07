package com.tuniu.car.basic.param.resolve.bean.wrapper;

import com.tuniu.operation.platform.tsg.base.core.method.SuccessData;
import org.springframework.core.MethodParameter;

import java.util.List;

/**
 * 
 * @copyright(C)  James
 * @author James
 */
public class PaginationListWrapper extends AbstractBeanWrapper {

    public boolean supports(MethodParameter returnType) {
        return List.class.isAssignableFrom(returnType.getParameterType());
    }

    public Object wrap(Object bean) {
        List<?> list = (List<?>) bean;
        Rows rows = new Rows();
        rows.setCount(list.size());
        rows.setRows(list);
        return new SuccessData(rows);
    }

    // public Integer getPriority() {
    // return 10;
    // }

    private class Rows {

        private Integer count;

        private List rows;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List getRows() {
            return rows;
        }

        public void setRows(List rows) {
            this.rows = rows;
        }

    }

}
