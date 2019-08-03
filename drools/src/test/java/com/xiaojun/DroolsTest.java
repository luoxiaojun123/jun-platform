package com.xiaojun;

import com.xiaojun.model.Fare;
import com.xiaojun.service.TaxiFareCalculatorService;
import com.xiaojun.model.TaxiRide;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author long.luo
 * @date 2018/9/26 11:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DroolsApplication.class)
@Slf4j
public class DroolsTest {

    @Autowired
    private TaxiFareCalculatorService taxiFareCalculatorService;

    @Test
    public void test() {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(false);
        taxiRide.setDistanceInMile(9L);

        Fare rideFare = new Fare();
        Long totalCharge = taxiFareCalculatorService.calculateFare(taxiRide, rideFare);

        System.out.println(totalCharge);
    }
}
