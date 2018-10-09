package com.xiaojun.cache;

/**
 * @author long.luo
 * @date 2018/10/9 10:55
 */
public interface UserService {

    /**
     * 删除
     *
     * @param user 用户对象
     * @return 操作结果
     */
    User saveOrUpdate(User user);

    /**
     * 添加
     *
     * @param id key值
     * @return 返回结果
     */
    User get(Long id);

    /**
     * 删除
     *
     * @param id key值
     */
    void delete(Long id);

    /**
     * 更新
     * @param user
     */
    void update(User user);
}
