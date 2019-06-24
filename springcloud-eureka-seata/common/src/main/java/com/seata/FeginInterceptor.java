package com.seata;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 服务调用传递header参数
 *
 * @author cjianyan@linewell.com
 */
public class FeginInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = getHttpServletRequest();
        Map<String, String> headers = null;
        if (request != null) {
            headers = getHeaders(request);
        }
        String xid = RootContext.getXID();
        System.out.println(xid+"=="+xid);
        if(headers==null){
            headers = new HashMap<>();
        }
        headers.put("Fescar-Xid", xid);

        if(headers!=null) {
            for (String headerName : headers.keySet()) {
                requestTemplate.header(headerName, headers.get(headerName));
            }
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes == null) {
                return null;
            }
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 遍历传递参数
     *
     * @param request
     * @return
     */
    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

}
