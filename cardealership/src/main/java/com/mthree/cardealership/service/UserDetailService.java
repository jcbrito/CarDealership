package com.mthree.cardealership.service;

import com.mthree.cardealership.dao.UserDao;
import com.mthree.cardealership.entities.Role;
import com.mthree.cardealership.entities.User;
import com.mthree.cardealership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Lewi
 */
@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        User user = userDao.getUserByEmail(email);
        if (user == null) throw new UsernameNotFoundException(email);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Role role : user.getRoles()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userDao.getUserByEmail(email);
//        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
//        return buildUserForAuthentication(user, authorities);
//    }
//
//    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
//        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
//        for (Role role : userRoles) {
//            roles.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
//        return grantedAuthorities;
//    }
//
//    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//                user.getActive(), true, true, true, authorities);
//    }
}
