package com.java.common.repository;

import com.java.common.entity.LoginHistories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistories,Integer> {
}
