package com.java.common.repository;

import com.java.api.controller.admin.studentmanager.dto.IStudentResponse;
import com.java.common.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value = "select std.id as id ,std.name as name,std.date_of_birth as datOfBirth,std.address as address,std.classroom_id as classroomId,student_course.course_id as courseId,student_course.point as point \n" +
            "from student std \n" +
            "inner join classroom \n" +
            "on std.classroom_id = classroom.id \n" +
            "inner join student_course \n" +
            "on std.id = student_course.student_id \n" +
            "where student_course.course_id = ?1 \n" +
            "or  (std.name  like ?2 \n" +
            "and classroom_id = ?3)",
            countQuery = "select * \n" +
            "from student std \n" +
            "inner join classroom \n" +
            "on std.classroom_id = classroom.id \n" +
            "inner join student_course\n" +
            "on std.id = student_course.student_id \n" +
            "where student_course.course_id = ?1 \n" +
            "or  (std.name  like ?2 \n" +
            "and classroom_id = ?3)", nativeQuery=true)
    Page<IStudentResponse> getAllStudent(Integer courseId, String name , Integer classroomId, Pageable page);
}
