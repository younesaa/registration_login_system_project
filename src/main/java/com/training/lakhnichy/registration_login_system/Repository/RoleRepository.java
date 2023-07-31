package com.training.lakhnichy.registration_login_system.Repository;

import com.training.lakhnichy.registration_login_system.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
