package com.xiaojun.service.impl;

import com.xiaojun.dto.security.CustomUser;
import com.xiaojun.entity.User;
import com.xiaojun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author long.luo
 * @date 2018/8/6
 */
@Service("customMobileUserDetailsService")
public class CustomMobileUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByMobile(username);

        List<String> roles = Arrays.asList(user.getRole().split(","));
        List<GrantedAuthority> authorities = roles.stream().map(role -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            return grantedAuthority;
        }).collect(Collectors.toList());

        CustomUser customUser = new CustomUser(user.getUsername(), user.getPassword(), authorities);
        return customUser;
    }

}
