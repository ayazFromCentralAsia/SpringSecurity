package com.example.demo.config;

import com.example.demo.Dao.DaoJpa;
import com.example.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    DaoJpa daoJpa;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = daoJpa.findByName(username);

        return user.map(MyUserDetails::new)
                .orElseThrow(() ->new UsernameNotFoundException(username + "There is not such"));
    }
}
