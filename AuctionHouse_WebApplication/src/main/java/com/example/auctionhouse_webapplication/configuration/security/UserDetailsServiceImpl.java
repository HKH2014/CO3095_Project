package com.example.auctionhouse_webapplication.configuration.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.auctionhouse_webapplication.model.Role;
import com.example.auctionhouse_webapplication.model.User;
import com.example.auctionhouse_webapplication.repo.RoleRepository;
import com.example.auctionhouse_webapplication.repo.UserRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User does not exist."));
        return new UserDetailsImpl(user);
    }
}
