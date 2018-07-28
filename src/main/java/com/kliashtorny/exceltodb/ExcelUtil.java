package com.kliashtorny.exceltodb;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anton Klyashtorny
 */
public class ExcelUtil {

    public static List<String> getColumnList(int index, Sheet sheet) {
        List<Cell> cells = new ArrayList<>();
        Row row = sheet.getRow(index);
        Iterator<Cell> cellIterator = row.cellIterator();
        cellIterator.forEachRemaining(cells::add);
        List<String> columnValues = cells.stream().map(Cell::getStringCellValue).collect(Collectors.toList());

        return columnValues;
    }

}
