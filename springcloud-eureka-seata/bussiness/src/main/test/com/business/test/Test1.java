package com.business.test;

import io.seata.sample.BusinessApplication;
import io.seata.sample.service.BusinessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: springcloud-eureka-seata
 * @description: 随便测试
 * @author: tjianqun@linewell.com
 * @create: 2019-06-15 11:28
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes={BusinessApplication.class})// 指定启动类
public class Test1 {

    @Autowired
    private BusinessService businessService;

    @Test
    public void test1(){
        try {
            businessService.purchase("U100000", "C100000", 200);
        } catch (Exception exx) {
            exx.printStackTrace();
        }
    }
}
