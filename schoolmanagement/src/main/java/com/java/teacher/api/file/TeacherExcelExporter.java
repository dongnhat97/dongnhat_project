package com.java.teacher.api.file;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.java.teacher.entity.Book;
import com.java.teacher.entity.ClassRoom;
import com.java.teacher.entity.DownloadRequest;
import com.java.teacher.entity.Teacher;
import com.java.teacher.repository.BookRepository;
import com.java.teacher.repository.ClassRoomRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TeacherExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFSheet sheetBook;
    private List<Teacher> listUsers;
    private BookRepository bookRepository;
    private ClassRoomRepository classRoomRepository;
    private XSSFSheet sheetClass;
    private Map<Integer, XSSFSheet> lissSheetTeacher = new HashMap<>();


    public TeacherExcelExporter(List<Teacher> listUsers, BookRepository bookRepository, ClassRoomRepository classRoomRepository) {

        this.listUsers = listUsers;
        this.bookRepository = bookRepository;
        this.classRoomRepository = classRoomRepository;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine(String action, DownloadRequest downloadRequest) {
        sheet = workbook.createSheet("Users");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());

        createCell(row, 0, "User ID", style);
        createCell(row, 1, "E-mail", style);
        createCell(row, 2, "Full Name", style);
        if (action.equals("CLASS")) {

            // dat sheet class cho giao vien
            listUsers.forEach(item ->{
                lissSheetTeacher.put(item.getId(),workbook.createSheet(item.getName()));

                Row rowClass = lissSheetTeacher.get(item.getId()).createRow(0);

                createCellClass(rowClass,0,"Class ID",style, item.getId());
                createCellClass(rowClass,1,"Class Name",style, item.getId());
                createCellClass(rowClass,2,"Quantity",style, item.getId());
                createCellClass(rowClass,3,"School",style, item.getId());
                createCellClass(rowClass,4,"Teacher ID",style, item.getId());
            });

        }else {
            sheetBook = workbook.createSheet("book");
            Row rowBook = sheetBook.createRow(0);
            createCellBook(rowBook, 0, "Book ID", style);
            createCellBook(rowBook, 1, "Price ID", style);
            createCellBook(rowBook, 2, "Quantity", style);
            createCellBook(rowBook, 3, "Title", style);
            createCellBook(rowBook, 4, "Total Manny", style);
            createCellBook(rowBook, 5, "Teacher Id", style);

        }
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue(value.toString());
        }
        cell.setCellStyle(style);
    }
    private void createCellBook(Row row, int columnCount, Object value, CellStyle style){
        sheetBook.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue( value.toString());

        }
        cell.setCellStyle(style);
    }
    private void createCellClass(Row row, int columnCount, Object value, CellStyle style, Integer idTeacher){
        lissSheetTeacher.get(idTeacher).autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue( value.toString());

        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        List<Book> getAllBook = bookRepository.findAll();
        int rowCount = 1;
        int rowCountBook = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Teacher user : listUsers) {
            int columnCount = 0;
            Row row = sheet.createRow(rowCount++);
            createCell(row, columnCount++, user.getId(), style);
            createCell(row, columnCount++, user.getName(), style);
            createCell(row, columnCount++, user.getAddress(), style);
                for (Book book : getAllBook) {
                    int columnCountBook = 0;
                    if (user.getId().equals(book.getTeacherId())) {
                        Row rowBook = sheetBook.createRow(rowCountBook++);
                        createCellBook(rowBook, columnCountBook++, book.getId(), style);
                        createCellBook(rowBook, columnCountBook++, book.getPrice(), style);
                        createCellBook(rowBook, columnCountBook++, book.getQuantity(), style);
                        createCellBook(rowBook, columnCountBook++, book.getTitle(), style);
                        createCellBook(rowBook, columnCountBook++, book.getTotalMoney(), style);
                        createCellBook(rowBook, columnCountBook++, book.getTeacherId(), style);
                    }


                }

        }
    }
//    private void writeDataLinesClass() {
//        List<ClassRoom> getAllClass = classRoomRepository.findAll();
//        int rowCount = 1;
//        int rowCountClass = 1;
//
//        CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setFontHeight(14);
//        style.setFont(font);
//
//        for (Teacher user : listUsers) {
//            int columnCount = 0;
//            Row row = sheet.createRow(rowCount++);
//            createCell(row, columnCount++, user.getId(), style);
//            createCell(row, columnCount++, user.getName(), style);
//            createCell(row, columnCount++, user.getAddress(), style);
//            for (ClassRoom classRoom : getAllClass) {
//                int columnCountClass = 0;
//                if (user.getId().equals(classRoom.getTeacherId())) {
//                    Row rowBook = sheetClass.createRow(rowCountClass++);
//                    createCellClass(rowBook, columnCountClass++, classRoom.getId(), style);
//                    createCellClass(rowBook, columnCountClass++, classRoom.getClassName(), style);
//                    createCellClass(rowBook, columnCountClass++, classRoom.getQuantity(), style);
//                    createCellClass(rowBook, columnCountClass++, classRoom.getSchool(), style);
//                    createCellClass(rowBook, columnCountClass++, classRoom.getTeacherId(), style);
//
//                }
//
//                if (user.getId().equals(classRoom.getTeacherId())) {
//                    Row rowBook = lissSheetTeacher.get(classRoom.getTeacherId()).createRow(rowCountClass++);
//                    createCellClass(rowBook, columnCountClass++, classRoom.getId(), style);
//                    createCellClass(rowBook, columnCountClass++, classRoom.getClassName(), style);
//                    createCellClass(rowBook, columnCountClass++, classRoom.getQuantity(), style);
//                    createCellClass(rowBook, columnCountClass++, classRoom.getSchool(), style);
//                    createCellClass(rowBook, columnCountClass++, classRoom.getTeacherId(), style);
//
//                }
//
//
//            }
//
//        }
//    }

    private void writeClass() {
        List<ClassRoom> getAllClass = classRoomRepository.findAll();
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Teacher user : listUsers) {
            int columnCount = 0;
            Row row = sheet.createRow(rowCount++);
            createCell(row, columnCount++, user.getId(), style);
            createCell(row, columnCount++, user.getName(), style);
            createCell(row, columnCount++, user.getAddress(), style);
            for (ClassRoom classRoom : getAllClass) {
//                int columnCountClass = 0;
                if (user.getId().equals(classRoom.getTeacherId())) {
                    int columnCountClass = 0;
                    Row rowClass = lissSheetTeacher.get(user.getId()).createRow(columnCountClass++);
                    createCellClass(rowClass, columnCountClass++, classRoom.getId(), style,user.getId());
                    createCellClass(rowClass, columnCountClass++, classRoom.getClassName(), style,user.getId());
                    createCellClass(rowClass, columnCountClass++, classRoom.getQuantity(), style,user.getId());
                    createCellClass(rowClass, columnCountClass++, classRoom.getSchool(), style,user.getId());
                    createCellClass(rowClass, columnCountClass, classRoom.getTeacherId(), style,user.getId());
                }
            }

        }
    }

    public void export(HttpServletResponse response, DownloadRequest downloadRequest) throws IOException {
        writeHeaderLine(downloadRequest.getAction(), downloadRequest);
        switch (downloadRequest.getAction()) {
            case "BOOK":
                writeDataLines();
                break;
            case "CLASS":
//                writeDataLinesClass();
                writeClass();
                break;

        }
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }


}