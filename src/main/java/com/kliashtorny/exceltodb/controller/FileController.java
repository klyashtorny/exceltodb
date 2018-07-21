package com.kliashtorny.exceltodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kliashtorny.exceltodb.service.FileService;

/**
 *
 * @author Anton Klyashtorny
 */
@RestController
public class FileController {

	@Autowired
	FileService fileService;
	
	@RequestMapping(value="/files",method=RequestMethod.POST)
	public String saveFile(@RequestParam("file") MultipartFile file){
		
		fileService.insertFile(file);
		
		return "file saved";
	}
	
}
