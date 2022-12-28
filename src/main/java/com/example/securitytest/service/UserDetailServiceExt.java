package com.example.securitytest.service;

import com.example.securitytest.domain.User;
import com.example.securitytest.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceExt implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailServiceExt(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDb = userRepository.findByUserName(username);
        if (userFromDb == null) throw new UsernameNotFoundException("FU");
        return org.springframework.security.core.userdetails.User.builder()
                .username(userFromDb.getUserName())
                .password(userFromDb.getPassword())
                .build();
    }
}
