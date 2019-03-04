package com.xiaojun.controller;

import com.xiaojun.model.Fare;
import com.xiaojun.model.TaxiRide;
import com.xiaojun.model.User;
import com.xiaojun.service.TaxiFareCalculatorService;
import com.xiaojun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author long.luo
 * @date 2019/3/4 10:45
 */
@RestController
public class TestDroolsController {

    @Autowired
    private TaxiFareCalculatorService taxiFareCalculatorService;

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(Long distanceInMile, boolean isNightSurcharge) {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(isNightSurcharge);
        taxiRide.setDistanceInMile(distanceInMile);

        Fare rideFare = new Fare();
        Long tax = taxiFareCalculatorService.calculateFare(taxiRide, rideFare);
        return "最终得分" + tax;
    }

    @GetMapping("/user/update")
    public String testUserUpdate(String username) {
        User user = new User();
        user.setUsername(username);
        user.setAge(20);
        User updateUser = userService.updateUser(user);

        return "年龄是:" + updateUser.getAge() + "密码是:" + updateUser.getPassword();
    }
}
