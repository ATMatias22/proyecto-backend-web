package com.sensor.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

import com.sensor.exception.GeneralException;
import org.aspectj.util.FileUtil;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

public final class FileHelper {

	private FileHelper() {

	}

	public static boolean createDirectory(File path) {
		if (!path.exists()) {
			return path.mkdir();
		}
		return false;
	}

	public static String renameFile(MultipartFile file, Integer id, String prefix) {
		String extension = FileHelper.getExtension(file.getOriginalFilename());
		return prefix + "_" + id + "." + extension;
	}

	public static void saveFile(MultipartFile file, File directory, String nameImage) throws IOException {
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

	public static boolean deleteDirectory(File directory) {
		return FileSystemUtils.deleteRecursively(directory);
	}

	public static String filePathToBase64String(String pathFile, String defaultPathIfPathFileIsNull){
		String path = pathFile == null ? defaultPathIfPathFileIsNull : pathFile;
		File file = new File(path);
		String stringBase64;
		try (FileInputStream fileInputStream = new FileInputStream(file)) {
			String extension = FileHelper.getExtension(file.getName());
			byte[] fileContent = FileUtil.readAsByteArray(file);
			stringBase64 = "data:image/" + extension + ";base64," + Base64.getEncoder().encodeToString(fileContent);
		} catch (IOException io) {
			throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Problemas al convertir la imagen");
		}
		return stringBase64;
	}


}
