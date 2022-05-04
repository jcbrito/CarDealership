package com.mthree.cardealership.repository;

import com.mthree.cardealership.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Lewi
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
//    Role findByRole(String name);
}
