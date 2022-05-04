package com.mthree.cardealership.service;

import com.mthree.cardealership.security.Encoder;
import com.mthree.cardealership.dao.UserDao;
import com.mthree.cardealership.entities.User;
import com.mthree.cardealership.repository.RoleRepository;
import com.mthree.cardealership.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lewi
 */
@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    private Encoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User saveUser(User user) {
        user.setPassword(this.bCryptPasswordEncoder.passwordEncoder().encode(user.getPassword()));
        user.setActive(true);
//        Role userRole = roleRepository.findByRole("ADMIN");
//        user.setRoles(new HashSet<>(roleRepository.findAll()));
        return userRepository.save(user);
    }
}
