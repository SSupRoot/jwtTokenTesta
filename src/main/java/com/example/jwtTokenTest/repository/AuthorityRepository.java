package com.example.jwtTokenTest.repository;

import com.example.jwtTokenTest.model.Authority;
import com.example.jwtTokenTest.model.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityEnum> {

    Optional<Authority> findByAuthorityStatus(AuthorityEnum authoritStatus);
}
