package com.java.teacher.api.file;

import com.java.teacher.entity.Teacher;
import com.java.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadTeacherService {
    private final TeacherRepository teacherRepository;
    //    upload file


    public void save(MultipartFile file) {

        try {
            List<Teacher> products = convertExcelToListOfTeacher(file.getInputStream());
            teacherRepository.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Teacher> getAllTeacher() {
        return this.teacherRepository.findAll();
    }

    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }


    //convert excel to list of teacher

    public static List<Teacher> convertExcelToListOfTeacher(InputStream is) {
        List<Teacher> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                Teacher teacher = new Teacher();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                        case 0:
                            teacher.setName(cell.getStringCellValue());
                            break;
                        case 1:
                            teacher.setAddress(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(teacher);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
}
