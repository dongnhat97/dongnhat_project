package com.java.api.controller.admin.studentmanager.dto;

import com.java.common.entity.Classroom;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
public class StudentResponse {

    private  Integer id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private Date dateOfBirth;

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    private Integer classroomId;

    private Integer point;

    private Integer courseId;

   public StudentResponse(IStudentResponse studentResponse) {
       this.id =  studentResponse.getId();
       this.name= studentResponse.getName();
       this.dateOfBirth = studentResponse.getDateOfBirth();
       this.address = studentResponse.getAddress();
       this.classroomId = studentResponse.getClassroomId();
       this.point = studentResponse.getPoint();
       this.courseId = studentResponse.getCourseId();
   }

}
