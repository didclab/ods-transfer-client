package com.ods.connector.FileShare.Model;

public class FileOperationRequest {
	private String fileUri;
	private String fileUriSrc;
	private String fileUriDest;

	public String getFileUri() {
		return fileUri;
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}

	public String getFileUriSrc() {
		return fileUriSrc;
	}

	public void setFileUriSrc(String fileUriSrc) {
		this.fileUriSrc = fileUriSrc;
	}

	public String getFileUriDest() {
		return fileUriDest;
	}

	public void setFileUriDest(String fileUriDest) {
		this.fileUriDest = fileUriDest;
	}

}
