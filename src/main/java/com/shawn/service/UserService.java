package com.shawn.service;

import com.shawn.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Xiaoyue Xiao
 */
public interface UserService extends UserDetailsService {

    Optional<User> getUserById(Long id);

    boolean saveUser(User user);

    boolean modifyUserOnPasswordById(User user);

    boolean deleteUserById(Long id);

}
