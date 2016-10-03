package com.socialnetwork.service;

import com.socialnetwork.entity.User;
import com.socialnetwork.entity.enums.UserRoleEnum;
import com.socialnetwork.repository.CrutchUserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = Logger.getLogger(CrutchUserRepository.class);
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        log.info("getting user with login - " + login);
        User user = userService.getUserByLogin(login);
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
        return userDetails;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
