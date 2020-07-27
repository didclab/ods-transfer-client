package com.ods.connector.FileShare.ServiceImpl;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import java.io.File;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ods.connector.FileShare.Model.Directory;

import static java.nio.file.StandardCopyOption.*;

@Service
public class FileManipulation {

//	public void listDirectoryTree(Path root) throws Exception{
//
//		if (Files.isDirectory(root)) {
//			final Directory rootDir = new Directory(root);
//
//			listDirectoryTree(rootDir);
//		} else {
//			throw new RuntimeException("Root needs to be a directory");
//		}
//	}
//
//	public void listDirectoryTree(Directory directory) throws Exception{
//
//		DirectoryStream<Path> stream = null;
//		try {
//			stream = Files.newDirectoryStream(directory.getPath());
//			for (Path path : stream) {
//				if (Files.isDirectory(path)) {
//					Directory child = new Directory(path);
//					directory.addDirectory(child);
//					System.out.println("Adding Directory: " + directory.getPath().toString() + "\\" + path.toString());
//					listDirectoryTree(child);
//				} else {
//					System.out.println("Adding files: " + path.toString());
//					directory.addFiles(path);
//				}
//			}
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		} finally {
//			if (!Objects.isNull(stream)) {
//				try {
//					stream.close();
//				} catch (IOException e) {
//					throw new RuntimeException("unable to close stream while listing directories", e);
//				}
//			}
//		}
//	}

	public void listDirectoryTree(Path root) {
		try {
			String jsonInString = new ObjectMapper().writerWithDefaultPrettyPrinter()
					.writeValueAsString(getDirectory(root));
			System.out.println(jsonInString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	private Directory getDirectory(Path path) {
		if (Files.isDirectory(path)) {
			return new Directory(path.toString(), path.toString(), "directory", getDirectoryList(path));
		} else {
			return new Directory(path.toString(), path.toString(), "file", null);
		}
	}

	private List<Directory> getDirectoryList(Path path) {
		List<Directory> dirList = new ArrayList<>();
		DirectoryStream<Path> stream = null;

		try {
			stream = Files.newDirectoryStream(path);
			for (Path pathS : stream) {
				dirList.add(getDirectory(pathS));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (!Objects.isNull(stream)) {
				try {
					stream.close();
				} catch (IOException e) {
					throw new RuntimeException("unable to close stream while listing directories", e);
				}
			}

		}
		return dirList;
	}

	public Directory getDirectoryContent(Path path) {
		Directory directory = null;
		try {
			directory = getDirectoryInfo(path);
			String jsonInString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(directory);
			System.out.println(jsonInString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return directory;
	}

	private Directory getDirectoryInfo(Path path) {
		Directory directory = null;
		if (Files.isDirectory(path)) {
			directory = new Directory(path.toString(), path.toString(), "directory", getDirPaths(path));
		} else {
			directory = getFileInDirectory(path);
		}
		return directory;
	}

	private List<Directory> getDirPaths(Path path) {
		List<Directory> dirList = new ArrayList<>();
		DirectoryStream<Path> stream = null;

		try {
			stream = Files.newDirectoryStream(path);
			for (Path pathS : stream) {
				dirList.add(getFileInDirectory(pathS));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (!Objects.isNull(stream)) {
				try {
					stream.close();
				} catch (IOException e) {
					throw new RuntimeException("unable to close stream while listing directories", e);
				}
			}

		}
		return dirList;
	}

	private Directory getFileInDirectory(Path path) {
		if (Files.isDirectory(path)) {
			return new Directory(path.toString(), path.toString(), "directory", null);
		} else {
			return new Directory(path.toString(), path.toString(), "file", null);
		}
	}

	public void deleteFile(Path path) throws Exception {
		try {
			System.out.println("! Deleting File From The Path !");
			Files.delete(path);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void createFolder(Path path) throws Exception {
		boolean dirExists = Files.exists(path);
		if (dirExists) {
			System.out.println("! Directory Already Exists !");
		} else {
			try {
				// Creating The New Directory Structure
				Files.createDirectories(path);
				System.out.println("! New Directory Successfully Created !");
			} catch (IOException ioExceptionObj) {
				System.out.println(
						"Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
			}
		}
	}

	public void createFile(Path path, byte[] b) throws Exception {
		if (Files.exists(path)) {
			System.out.println("File Already Exists!!");
		} else {
			try {
				if (b != null) {
					Files.write(path, b);
				} else {
					Files.createFile(path);
				}
				System.out.println("! New File Successfully Created !");
			} catch (IOException ioExceptionObj) {
				System.out.println("Problem Occured While Creating The file = " + ioExceptionObj.getMessage());
			}
		}
	}
	
	
	public void copyFolder(File sourceFolder, File destinationFolder) throws IOException
    {
        if (sourceFolder.isDirectory()) 
        {
            if (!destinationFolder.exists()) 
            {
                destinationFolder.mkdir();
                System.out.println("Directory created :: " + destinationFolder);
            }
             
            String files[] = sourceFolder.list();
            
            for (String file : files) 
            {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);
            
                copyFolder(srcFile, destFile);
            }
        }
        else
        { 
            Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), REPLACE_EXISTING);
            System.out.println("File copied :: " + destinationFolder);
        }
    }

	public void copyFolder(Path src, Path dest) throws Exception {
		try (Stream<Path> stream = Files.walk(src)) {
			stream.forEach(source -> {
				try {
					copy(source, dest.resolve(src.relativize(source)));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new RuntimeException("Exception in copy files", e);
				}
			});
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	public void copy(Path source, Path dest) throws Exception {
		try {
			Files.copy(source, dest, REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public void deleteFolder(Path path) throws IOException {
		if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
			try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
				for (Path entry : entries) {
					deleteFolder(entry);
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		Files.delete(path);
	}

	public void readFile(Path path) {

	}

//	public static void main(String args[]) {
//		Path root = Paths.get("C:\\Users\\13107\\Downloads\\LATIMES\\test.txt");
//		Path notExist = Paths.get("C:\\Users\\13107\\Downloads\\LATIMES\\test1.txt");
//		Path newFolder = Paths.get("C:\\Users\\13107\\Downloads\\LATIMES\\test");
//		Path srcFolder = Paths.get("C:\\Users\\13107\\Downloads\\LATIMES\\LATIMES\\backup_sol");
////		listDirectoryTree(root);
////		createFile(root, null);
////		deleteFile(root);
////		createFolder(newFolder);
//		try {
////			copyFolder(srcFolder, newFolder);
//			deleteFolder(newFolder);
//		} catch (Exception e) {
//			System.out.println("Exception" + e.toString());
//			e.printStackTrace();
//		}
//		
//	}
}
