package com.kliashtorny.exceltodb.repository;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Anton Klyashtorny
 */
public interface JdbcExcel {

	public boolean insertFile(MultipartFile file);
	
}
