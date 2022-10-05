package com.java.common.repository;

import com.java.common.entity.UserSeminar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSeminarRepository extends JpaRepository<UserSeminar,Integer> {
 Optional<UserSeminar> findUserSeminarBySeminar_Id(Integer id);
}
