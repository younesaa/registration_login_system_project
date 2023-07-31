package com.training.lakhnichy.registration_login_system.Repository;

import com.training.lakhnichy.registration_login_system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
