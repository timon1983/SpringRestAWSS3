package com.example.SpringBootRestAWSS3.security;

import com.example.SpringBootRestAWSS3.model.User;
import com.example.SpringBootRestAWSS3.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserAuthRepository userAuthRepository;

    @Autowired
    public UserDetailsServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userAuthRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("UserAuth does not exists"));
        return SecurityUser.fromUserAuth(user);
    }
}
