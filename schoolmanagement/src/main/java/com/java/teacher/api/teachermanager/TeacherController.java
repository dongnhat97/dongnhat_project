package com.java.teacher.api.teachermanager;

//import com.java.teacher.entity.Teacher;

import com.java.common.response.APIResponse;
import com.java.teacher.api.file.UploadTeacherService;
import com.java.teacher.api.file.UserExcelExporter;
import com.java.teacher.entity.Teacher;
import com.java.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    UploadTeacherService uploadTeacherService;



    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Teacher> listUsers = teacherRepository.findAll();

        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);

        excelExporter.export(response);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (UploadTeacherService.checkExcelFormat(file)) {
            //true

            this.uploadTeacherService.save(file);

            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }

    @GetMapping("/product")
    public List<Teacher> getAllProduct() {
        return this.uploadTeacherService.getAllProducts();
    }

}
