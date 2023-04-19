package com.test.services.secure;

import com.test.entity.secure.Role;

import com.test.entity.secure.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.List;

public interface UserService extends UserDetailsService {

    User findUserBYId(Long id);
    List<User> allUsers();

    boolean saveUser(User user);

    boolean deleteUser(Long userId);

    boolean updateUser(Long userId, User user);

    void newUpdateUser(User user, List<Role> role);

    User findByUsername(String username);


}