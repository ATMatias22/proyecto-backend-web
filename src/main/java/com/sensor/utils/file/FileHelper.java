package com.sensor.utils.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public final class FileHelper {
	
	private FileHelper() {
		
	}
	
	public static void createDirectory(File directory) {
		if (!directory.exists()) {
			if (directory.mkdir()) {
			} else {
				System.out.println("error al crear directorio");
			}
		}
	}
	
	public static String renameFile(MultipartFile file, Long productId) {
		String extension = FileHelper.getExtension(file.getOriginalFilename());
		String newNameImage = "image" + productId + "." + extension;
		return newNameImage;
	}
	
	public static void saveFile(MultipartFile file,File directory, String nameImage) throws IOException {
		Files.copy(file.getInputStream(), directory.toPath().resolve(nameImage),
				StandardCopyOption.REPLACE_EXISTING);
	}
	
	public static String getExtension(String fileName) {
		String extension = "";
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1);
		}

		return extension;
	}

}
