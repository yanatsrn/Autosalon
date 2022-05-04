package com.pluralsight.conference.report;

import com.pluralsight.conference.entity.Car;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.SQLException;
import java.util.List;

public class CarReport {
    public CarReport() {
    }
    public static void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Название");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Год");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Пробег");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Топливо");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Расход топлива");

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Коробка передач");

        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("Цена");

        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("Компания");

        headerCell = headerRow.createCell(8);
        headerCell.setCellValue("Тип");

        headerCell = headerRow.createCell(9);
        headerCell.setCellValue("Салон");

        headerCell = headerRow.createCell(10);
        headerCell.setCellValue("Цвет");

        headerCell = headerRow.createCell(10);
        headerCell.setCellValue("Парктроние");
    }

    public static void writeDataLines(List<Car> cars, XSSFWorkbook workbook,
                                      XSSFSheet sheet) throws SQLException {
        int rowCount = 1;
        for (Car c : cars) {
            if (!c.isBought()) {
                String name = c.getName();
                String year = String.valueOf(c.getYear());
                String distance = String.valueOf(c.getDistance());
                String fuel = c.getFuel();
                String fuelConsumption = c.getFuelConsumption();
                String transmission = c.getTransmission();
                String price = String.valueOf(c.getPrice());
                String companyName = c.getCompany().getCompanyName();
                String salon = c.getTypeCar().getSalon();
                String body = c.getTypeCar().getBody();
                String color = c.getTypeCar().getColor();
                String parkingHelper = String.valueOf(c.getTypeCar().isParkingHelper());

                Row row = sheet.createRow(rowCount++);

                int columnCount = 0;
                Cell cell = row.createCell(columnCount++);
                cell.setCellValue(name);

                cell = row.createCell(columnCount++);
                cell.setCellValue(year);

                cell = row.createCell(columnCount++);
                cell.setCellValue(distance);

                cell = row.createCell(columnCount++);
                cell.setCellValue(fuel);

                cell = row.createCell(columnCount++);
                cell.setCellValue(fuelConsumption);

                cell = row.createCell(columnCount++);
                cell.setCellValue(transmission);

                cell = row.createCell(columnCount++);
                cell.setCellValue(price);

                cell = row.createCell(columnCount++);
                cell.setCellValue(companyName);

                cell = row.createCell(columnCount++);
                cell.setCellValue(body);

                cell = row.createCell(columnCount++);
                cell.setCellValue(salon);

                cell = row.createCell(columnCount++);
                cell.setCellValue(color);

                cell = row.createCell(columnCount++);
                cell.setCellValue(parkingHelper);
            }


        }
    }
}
