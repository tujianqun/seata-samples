package io.seata.sample;

import com.seata.FeginInterceptor;
import com.seata.FescarXidFilter;
import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author jimin.jm@alibaba-inc.com
 * @date 2019/06/14
 */
@SpringBootApplication
@EnableFeignClients
public class BusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }


    @Bean
    public FescarXidFilter fescarXidFilter(){
        return new FescarXidFilter();
    }


    @Bean
    public FeginInterceptor feginInterceptor(){
        return new FeginInterceptor();
    }

    @Bean
    public GlobalTransactionScanner scanner(){
        GlobalTransactionScanner scanner = new GlobalTransactionScanner("fescar-test","my_test_tx_group");
        return scanner;
    }

}
