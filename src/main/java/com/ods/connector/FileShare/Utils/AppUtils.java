package com.ods.connector.FileShare.Utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class AppUtils {

	private Path root = Paths.get("/Users/anujbhargava7/Documents/Course/SEM2/ODS/sharedFolder");
	private Path notExist = Paths.get("/Users/anujbhargava7/Documents/Course/SEM2/ODS/sharedFolder");
	private Path newFolder = Paths.get("/Users/anujbhargava7/Documents/Course/SEM2/ODS/sharedFolder");
	private Path srcFolder = Paths.get("/Users/anujbhargava7/Documents/Course/SEM2/ODS/sharedFolder");

	public Path getSharedFolderPath() {
		return this.notExist;
	}

	public void setSharedFolderPath(String srcFolderPath) {
		this.srcFolder = Paths.get(srcFolderPath);
	}
	
	public Path getPathFromFile(String fileUri) {
		Path path = Paths.get(fileUri);
		return path;
	}
	
	public File getFileFromPath(String filePath) {
		return new File(filePath);
	}

}
