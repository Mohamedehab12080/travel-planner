package com.fawry.user.repository;

import com.fawry.common.repository.BaseRepositoryImpl;
import com.fawry.user.api.repository.UserRepository;
import com.fawry.user.model.entity.User;
import com.fawry.user.model.enums.Role;
import com.fawry.user.repository.jpa.UserJPARepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User,Long> implements UserRepository {

    private final UserJPARepository jpaRepository;

    public UserRepositoryImpl(UserJPARepository jpaRepository) {
        super(jpaRepository);
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User insert(User user) {
        user.setCreatedOn(LocalDateTime.now());
        return jpaRepository.save(user);
    }

    @Override
    public void update(User user) {
        user.setLastModifiedOn(LocalDateTime.now());
        jpaRepository.save(user);
    }

    @Override
    public Optional<User> selectByEmail(String email) {
        return jpaRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> selectByEmailAndIsActive(String email, boolean isActive) {
        return jpaRepository.findByEmailAndIsActive(email, isActive);
    }

    @Override
    public List<User> selectByRole(Role role) {
        return jpaRepository.findByRole(role);
    }
}
