package com.kliashtorny.exceltodb;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_NULL_AND_BLANK;

/**
 * @author Anton Klyashtorny
 */
public class ExcelUtil {

    public static List<String> getColumnList(int index, Sheet sheet) {
        Row row = sheet.getRow(index);
        List<Cell> cells = IntStream
                .range(0, row.getLastCellNum())
                .mapToObj(i -> row.getCell(i, RETURN_NULL_AND_BLANK))
                .collect(Collectors.toList());
        DataFormatter df = new DataFormatter();
        List<String> cell = cells.stream().map(df::formatCellValue).collect(Collectors.toList());

        return cell;
    }
}
