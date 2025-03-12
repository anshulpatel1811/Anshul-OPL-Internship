package com.user.services.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.user.exception.BadApiRequest;
import com.user.services.FileService;

public class FileServiceImpl implements FileService{

	@Override
	public String uploadFile(MultipartFile file, String path) throws IOException {
		
		// orignal-file name
		String originalFilename = file.getOriginalFilename();
		// random file name
		UUID filename = UUID.randomUUID();
		
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
		
		String fileNameWithExtension = filename+extension;
		
		String fullPathWithExtension = path+fileNameWithExtension;
		
		if(extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpeg")) {
			
			File folder = new File(path);
			
			if(!folder.exists()) {
				folder.mkdirs();
			}
			Files.copy(file.getInputStream(), Paths.get(fullPathWithExtension));
			return fileNameWithExtension;
		}else {
			throw new BadApiRequest("File with this "+extension+" not allowed !!");
		}
	
	}

	@Override
	public InputStream getResource(String path, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
