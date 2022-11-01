package com.java.common.repository;

import com.java.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
 User findByUserName(String userName);
 Optional<User> findByEmail (String email);

 @Query(value = "update user set status = 1 where email = ?1", nativeQuery =true)
 void enableActiveAccount (String email);

 User findUserByEmail(String email);

}
