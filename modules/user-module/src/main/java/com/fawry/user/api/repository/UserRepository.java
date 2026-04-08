package com.fawry.user.api.repository;

import com.fawry.common.api.repository.BaseRepository;
import com.fawry.user.model.entity.User;
import com.fawry.user.model.enums.Role;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends BaseRepository<User, Long> {

    User insert(User user);
    void update(User user);
    Optional<User> selectByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> selectByEmailAndIsActive(String email,boolean isActive);
    List<User> selectByRole(Role role);
 }
