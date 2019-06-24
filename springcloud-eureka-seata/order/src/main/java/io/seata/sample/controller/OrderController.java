package io.seata.sample.controller;

import io.seata.sample.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jimin.jm@alibaba-inc.com
 * @date 2019/06/14
 */

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static AtomicInteger index = new AtomicInteger(1);

    @GetMapping("/create")
    public Boolean create(String userId, String commodityCode, Integer count) {

        orderService.create(userId, commodityCode, count);

        if(index.intValue()%2==0){
            //throw new RuntimeException("异常抛出");
        }
        index.incrementAndGet();
        return true;
    }

}
