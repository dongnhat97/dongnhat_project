package com.java.common.repository;

import com.java.common.entity.VerifyMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerifyMailRepository  extends JpaRepository<VerifyMail,Integer> {
    Optional<VerifyMail> findByToken(String token);
}
