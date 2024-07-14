package com.alurachallenge.forohub.repository;

import com.alurachallenge.forohub.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByActiveTrue(Pageable pageable);
    UserDetails findByEmail(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
