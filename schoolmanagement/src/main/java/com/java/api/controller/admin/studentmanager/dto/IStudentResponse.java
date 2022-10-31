package com.java.api.controller.admin.studentmanager.dto;

import java.sql.Date;

public interface IStudentResponse {

    Integer getId();

    Integer getCourseId();

    String getName();

    Integer getClassroomId();

    Date getDateOfBirth();

    String getAddress();

    Integer getPoint();


}
