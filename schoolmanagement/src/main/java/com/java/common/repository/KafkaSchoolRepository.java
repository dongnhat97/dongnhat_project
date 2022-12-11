package com.java.common.repository;

import com.java.common.entity.KafkaSchool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KafkaSchoolRepository extends JpaRepository<KafkaSchool,Integer> {
}
