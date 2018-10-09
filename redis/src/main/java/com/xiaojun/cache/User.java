package com.xiaojun.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author long.luo
 * @date 2018/10/9 10:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private Long id;
    private String username;
    private String password;
}
