package com.tuniu.car.basic.interceptor;

import com.tuniu.car.basic.constants.Constants;
import com.tuniu.car.basic.interceptor.context.InvokeContext;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;


/**
 * 防重复提交任务
 *
 * @author James
 */
public class PreventRepeatSubmitAction implements FilterAction {
    @Value(value = "@{sys.code}")
    private String sysCode;

    @Override
    public void invoke(InvokeContext invokeContext) throws Exception {
        HttpServletRequest request = invokeContext.getRequest();
        if (!invokeContext.includeRequestURI()) {
            invokeContext.invokeNext();
            return;
        }
        String seqNo = request.getHeader(Constants.TN_SEQNO);
        String source = request.getHeader(Constants.TN_SOURCE);
        /*if (StringUtils.isNotBlank(seqNo) && StringUtils.isNotBlank(source)) {
            // 1.判断是否有调用记录
            if (invokeContext.isExistInvokeRequestLog()) { // 有
                final InvokeLog responseInvokeLog = CommonInvokeLogUtil.queryInvokeLogBySeqNo(seqNo, source,
                		sysCode, Constants.INVOKE_TYPE_RESPONSE);

                // 1.1如果有响应记录
                if (responseInvokeLog != null) {
                    // 直接返回响应
                    invokeContext.sendResponseData(responseInvokeLog.getContent(), Constants.TN_STATUS_OK,
                            HttpServletResponse.SC_OK);
                } else {// 1.2 没有响应记录，返回重试状态码
                    invokeContext.sendResponseData(StringUtils.EMPTY, Constants.TN_STATUS_REPEAT,
                            HttpServletResponse.SC_OK);
                }
                invokeContext.complete();
                return;
            }
        }*/
        // 2 继续传递
        invokeContext.invokeNext();
    }

}
