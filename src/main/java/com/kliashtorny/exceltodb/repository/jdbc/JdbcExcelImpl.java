package com.kliashtorny.exceltodb.repository.jdbc;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.kliashtorny.exceltodb.repository.JdbcExcel;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import static com.kliashtorny.exceltodb.ExcelUtil.getColumnList;

/**
 * @author Anton Klyashtorny
 */
@Repository
public class JdbcExcelImpl implements JdbcExcel {

    private static final String FILEDB_TABLE = "filedb.`table`";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS `table`";

    public boolean insertFile(MultipartFile file) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/filedb", "root", "root");
            con.setAutoCommit(false);
            PreparedStatement pstmt = null;
            InputStream inputStream = new BufferedInputStream(file.getInputStream());

            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            List<String> columnValues = getColumnList(0, sheet);
            pstmt = (PreparedStatement) con.prepareStatement(DROP_TABLE);
            pstmt.execute();

            String createTable = tableCreate(columnValues);
            pstmt = (PreparedStatement) con.prepareStatement(createTable);
            pstmt.execute();

            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                List<String> columnInsert = getColumnList(i, sheet);
                if (columnInsert.size() > 0) {
                    String tableInsert = tableInsert(columnInsert);
                    pstmt = (PreparedStatement) con.prepareStatement(tableInsert);
                    pstmt.execute();
                }
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

    private String tableCreate(List<String> columnNames) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + FILEDB_TABLE + " (");
        columnNames.forEach(columnName -> sb.append(columnName).append(" VARCHAR(255) NOT NULL, "));
        sb.replace(sb.length() - 2, sb.length(), " ");
        sb.append(");");

        return sb.toString();
    }

    private String tableInsert(List<String> columnInsert) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO " + FILEDB_TABLE + " VALUES(");
        columnInsert.forEach(columnConsumer -> sb.append("'").append(columnConsumer).append("'").append(" , "));
        sb.replace(sb.length() - 2, sb.length(), " ");
        sb.append(");");

        return sb.toString();
    }
}
