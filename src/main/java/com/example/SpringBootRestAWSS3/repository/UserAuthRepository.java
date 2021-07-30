package com.example.SpringBootRestAWSS3.repository;

import com.example.SpringBootRestAWSS3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findByName(String name);
}
