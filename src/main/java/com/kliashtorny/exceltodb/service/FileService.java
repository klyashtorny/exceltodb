package com.kliashtorny.exceltodb.service;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Anton Klyashtorny
 */
public interface FileService {

	public boolean insertFile(MultipartFile file);
}
