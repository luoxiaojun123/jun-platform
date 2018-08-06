package com.xiaojun.repository;

import com.xiaojun.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository
 *
 * @author long.luo
 * @date 2018-07-31
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    /**
     * 根据用户名获取user
     *
     * @param username
     * @return
     */
    User findByUsername(String username);


    /**
     * 根据手机号码获取user
     *
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);
}
