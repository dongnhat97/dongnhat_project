package com.java.common.repository;

import com.java.common.entity.ParamCaching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeminarParamRepository extends JpaRepository<ParamCaching,Integer> {

}
