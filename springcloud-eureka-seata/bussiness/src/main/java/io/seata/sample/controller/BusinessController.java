package io.seata.sample.controller;

import io.seata.sample.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jimin.jm@alibaba-inc.com
 * @date 2019/06/14
 */

@RestController
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /**
     * 购买下单，模拟全局事务提交
     *
     * @return
     */
    @RequestMapping("/purchase/commit/{orderCount}")
    public String purchaseCommit(@PathVariable  Integer orderCount) {
        try {
            businessService.purchase("U100000", "C100000", orderCount);
        } catch (Exception exx) {
            return exx.getMessage();
        }
        return "全局事务提交";
    }

    /**
     * 购买下单，模拟全局事务回滚
     * 账户或库存不足
     *
     * @return
     */
    @RequestMapping("/purchase/rollback")
    public String purchaseRollback() {
        try {
            businessService.purchase("U100000", "C100000", 10000);
        } catch (Exception exx) {
            return exx.getMessage();
        }
        return "全局事务提交";
    }
}
