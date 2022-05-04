package com.pluralsight.conference.report;

import com.pluralsight.conference.entity.Person;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.SQLException;
import java.util.List;

public class PeopleReport {

    public PeopleReport() {
    }
    public static void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("person_id");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("surname");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("name");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("age");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("phone");

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("mail");

        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("user_id");

        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("login");

        headerCell = headerRow.createCell(8);
        headerCell.setCellValue("role");

        headerCell = headerRow.createCell(9);
        headerCell.setCellValue("discount_sum");

        headerCell = headerRow.createCell(10);
        headerCell.setCellValue("active");
    }

    public static void writeDataLines(List<Person> people, XSSFWorkbook workbook,
                                      XSSFSheet sheet) throws SQLException {
        int rowCount = 1;
        for (Person p : people) {
            String personId = String.valueOf(p.getPersonId());
            String surname = p.getSurname();
            String name = p.getName();
            String age = String.valueOf(p.getAge());
            String phone = p.getPhone();
            String mail = p.getMail();
            String userId = String.valueOf(p.getUser().getUserId());
            String login = p.getUser().getLogin();
            String role = p.getUser().getRole();
            String active = String.valueOf(p.getUser().isActive());
            String discountSum = String.valueOf(p.getUser().getDiscountSum());

            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(personId);

            cell = row.createCell(columnCount++);
            cell.setCellValue(surname);

            cell = row.createCell(columnCount++);
            cell.setCellValue(name);

            cell = row.createCell(columnCount++);
            cell.setCellValue(age);

            cell = row.createCell(columnCount++);
            cell.setCellValue(phone);

            cell = row.createCell(columnCount++);
            cell.setCellValue(mail);

            cell = row.createCell(columnCount++);
            cell.setCellValue(userId);

            cell = row.createCell(columnCount++);
            cell.setCellValue(login);

            cell = row.createCell(columnCount++);
            cell.setCellValue(role);

            cell = row.createCell(columnCount++);
            cell.setCellValue(discountSum);

            cell = row.createCell(columnCount++);
            cell.setCellValue(active);


        }
    }
}
