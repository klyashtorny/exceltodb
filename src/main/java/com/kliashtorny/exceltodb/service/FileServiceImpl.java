package com.kliashtorny.exceltodb.service;

import com.kliashtorny.exceltodb.repository.JdbcExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Anton Klyashtorny
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    JdbcExcel jdbcExcel;

    public boolean insertFile(MultipartFile file) {

        return jdbcExcel.insertFile(file);

    }

}
