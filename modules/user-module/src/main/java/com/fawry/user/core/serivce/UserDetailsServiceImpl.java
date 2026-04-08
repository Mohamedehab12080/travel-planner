// user-module/src/main/java/com/fawry/user/config/UserDetailsServiceImpl.java
package com.fawry.user.core.serivce;

import com.fawry.common.model.exception.BusinessException;
import com.fawry.common.security.model.CustomUserDetails;
import com.fawry.user.api.repository.UserRepository;
import com.fawry.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.fawry.user.model.enums.UserErrors.USER_NOT_FOUND;

@Slf4j
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Loading user by email: {}", email);

        User user = userRepository.selectByEmail(email)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND, email));

        log.info("User role from database: {}", user.getRole().name());  // Should print: ROLE_ADMIN

        return CustomUserDetails.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().name())
                .build();
    }
}