package com.ods.connector.FileShare.Controller;

import java.nio.file.Path;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ods.connector.FileShare.Model.FileOperationRequest;
import com.ods.connector.FileShare.ServiceImpl.FileManipulation;
import com.ods.connector.FileShare.Utils.AppUtils;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	FileManipulation fileManipulation;

	@Autowired
	AppUtils appUtils;

	@RequestMapping(value = "/create/file", method = RequestMethod.POST)
	public ResponseEntity<?> createFile(@RequestBody String fileUri, HttpServletResponse response) {

		try {

			fileManipulation.createFile(appUtils.getPathFromFile(fileUri), null);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/create/folder", method = RequestMethod.POST)
	public ResponseEntity<?> createFolder(@RequestBody String fileUri, HttpServletResponse response) {

		try {

			fileManipulation.createFolder(appUtils.getPathFromFile(fileUri));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteFiles(@RequestBody String fileUri, HttpServletResponse response) {

		try {

			fileManipulation.deleteFolder(appUtils.getPathFromFile(fileUri));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/share", method = RequestMethod.POST)
	public ResponseEntity<?> shareFiles(@RequestBody String fileUri, HttpServletResponse response) {

		try {
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/copy", method = RequestMethod.POST)
	public ResponseEntity<?> copyFiles(@RequestBody FileOperationRequest fileRequest,
			HttpServletResponse response) {

		try {

			fileManipulation.copyFolder(appUtils.getFileFromPath(fileRequest.getFileUriSrc()), appUtils.getFileFromPath(fileRequest.getFileUriDest()));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public ResponseEntity<?> showFiles(@RequestBody String fileUri, HttpServletResponse response) {

		try {
			// fileManipulation.getDirectoryContent(appUtils.getPathFromFile(fileUri));
			return new ResponseEntity<>(fileManipulation.getDirectoryContent(appUtils.getPathFromFile(fileUri)),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
