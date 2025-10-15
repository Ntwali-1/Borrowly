package com.example.Borrowly.services;

import com.example.Borrowly.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
public class UserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            var user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
            return new User(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.emptyList()
            );
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
