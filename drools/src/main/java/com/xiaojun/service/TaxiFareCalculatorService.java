package com.xiaojun.service;

import com.xiaojun.model.Fare;
import com.xiaojun.model.TaxiRide;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author long.luo
 * @date 2019/3/4 10:01
 */
@Service
public class TaxiFareCalculatorService {

    @Autowired
    private KieContainer kieContainer;

    public Long calculateFare(TaxiRide taxiRide, Fare rideFare) {
        KieSession kieSession = kieContainer.newKieSession();

        kieSession.setGlobal("rideFare", rideFare);
        kieSession.insert(taxiRide);
        kieSession.fireAllRules();
        kieSession.dispose();

        return rideFare.getTotalFare();
    }
}
