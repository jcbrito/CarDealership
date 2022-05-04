package com.mthree.cardealership.repository;

import com.mthree.cardealership.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lewi
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
