package com.kliashtorny.exceltodb;

import org.apache.poi.ss.usermodel.*;

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
        DataFormatter df = new DataFormatter();
        List<String> cell = cells.stream().map(df::formatCellValue).collect(Collectors.toList());

        return cell;
    }

}
