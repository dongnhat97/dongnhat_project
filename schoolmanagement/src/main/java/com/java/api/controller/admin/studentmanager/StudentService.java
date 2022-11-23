package com.java.api.controller.admin.studentmanager;

import com.java.api.controller.admin.studentmanager.dto.IStudentResponse;
import com.java.api.controller.admin.studentmanager.dto.StudentResponse;
import com.java.common.entity.Student;
import com.java.common.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Page<StudentResponse> getAllStudent(Pageable pageable, Integer courseId, Integer limit, String name, Integer classroomId) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),limit);
        Page<IStudentResponse> studentPage = studentRepository.getAllStudent(courseId, "%"+ name+ "%", classroomId, pageRequest);
        //        studentList.stream().map(i -> {
//            StudentResponse studentResponse = new StudentResponse();
//            studentResponse.setAddress(i.getAddress());
//            studentResponse.setClassroom(i.getClassroom());
//            studentResponse.setDateOfBirth(i.getDateOfBirth());
//            studentResponse.setName(i.getName());
//            studentNew.add(studentResponse);
//            return studentResponse;
//        }).collect(Collectors.toList());
        return studentPage.map(StudentResponse::new);

    }
}
