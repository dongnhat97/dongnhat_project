package com.java.api.controller.admin.studentmanager;

import com.java.api.controller.admin.studentmanager.dto.StudentResponse;
import com.java.common.entity.Student;
import com.java.common.page.PageInfo;
import com.java.common.response.APIResponse;
import com.java.utils.UtilsData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private  final StudentService studentService;

    private static final String GET_ALL = "/list";
    @GetMapping("/list")
    public APIResponse<PageInfo<StudentResponse>> getAllStudent(@PageableDefault Pageable pageable,
                                               @RequestParam Integer courseId,
                                               @RequestParam (defaultValue = "10") Integer limit,
                                               @RequestParam String name,
                                               @RequestParam Integer classroom) {
        Page<StudentResponse> studentPage = this.studentService.getAllStudent(pageable,courseId,limit,name,classroom);
        return APIResponse.okStatus(UtilsData.pagingResponse(studentPage));

    }
}
