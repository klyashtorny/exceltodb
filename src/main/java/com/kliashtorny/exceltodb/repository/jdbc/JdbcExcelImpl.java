package com.kliashtorny.exceltodb.repository.jdbc;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.kliashtorny.exceltodb.repository.JdbcExcel;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 *
 * @author Anton Klyashtorny
 */
@Repository
public class JdbcExcelImpl implements JdbcExcel {

    private static final String FILEDB_TABLE = "filedb.`table`";

    public boolean insertFile(MultipartFile file) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/filedb", "root", "root");
            con.setAutoCommit(false);
            PreparedStatement pstmt = null;
            InputStream inputStream = new BufferedInputStream(file.getInputStream());

            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);
            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                String segment = row.getCell(0).getStringCellValue();
                String country = row.getCell(1).getStringCellValue();
                String product = row.getCell(2).getStringCellValue();
                int unitssold = (int) row.getCell(3).getNumericCellValue();
                int saleprice = (int) row.getCell(4).getNumericCellValue();
                int grosssale = (int) row.getCell(5).getNumericCellValue();
                int profit = (int) row.getCell(6).getNumericCellValue();

                String sql = "INSERT INTO " + FILEDB_TABLE + " VALUES('" + segment +
                        "','" + country + "','" + product + "','"
                        + unitssold + "','" + saleprice + "','"
                        + grosssale + "','" + profit + "')";
                pstmt = (PreparedStatement) con.prepareStatement(sql);
                pstmt.execute();
            }
            con.commit();
            pstmt.close();
            con.close();
            inputStream.close();
        } catch (ClassNotFoundException e) {

        } catch (SQLException ex) {

        } catch (IOException ioe) {

        } catch (EncryptedDocumentException e) {

        } catch (InvalidFormatException e) {

        }

        return false;
    }


}
