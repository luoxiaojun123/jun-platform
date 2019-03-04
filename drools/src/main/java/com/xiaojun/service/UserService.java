package com.xiaojun.service;

import com.xiaojun.model.User;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author long.luo
 * @date 2019/3/4 11:23
 */
@Service
public class UserService {

    @Autowired
    private KieContainer kieContainer;

    public User updateUser(User user) {

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(user);
        kieSession.fireAllRules();
        kieSession.dispose();

        return user;
    }
}
