package com.LiveConnect.Service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Here, you would typically retrieve the user details from the database.
        // For demonstration, we'll use a hardcoded user.
        if ("user".equals(username)) {
            return User.withUsername("user")
                    .password("{noop}password")  // `{noop}` for plain text, replace with encoded passwords
                    .roles("USER")
                    .build();
        } else if ("admin".equals(username)) {
            return User.withUsername("admin")
                    .password("{noop}adminpassword")
                    .roles("ADMIN")
                    .build();
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }
}
