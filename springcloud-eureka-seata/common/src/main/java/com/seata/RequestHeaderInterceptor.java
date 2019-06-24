package com.seata;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import org.apache.commons.lang.StringUtils;

/**
 * @program: springboot-fesartest
 * @description:
 * @author: tjianqun@linewell.com
 * @create: 2019-02-14 11:12
 **/

public class RequestHeaderInterceptor implements RequestInterceptor {
    public RequestHeaderInterceptor(){
        System.out.println("初始化RequestHeaderInterceptor");
    }
    @Override
    public void apply(RequestTemplate template) {
        String xid = RootContext.getXID();
        if(StringUtils.isNotBlank(xid)){
            template.header("Fescar-Xid",xid);
        }
    }
}