package com.emp.security.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

		// upload
		String uploadFile(MultipartFile file,String path) throws IOException;
		
		// get file
		InputStream getResource(String path,String name) throws FileNotFoundException;
		
//		// get file by byte[]
//		byte[] getImage(String path,String name) throws IOException;
}
