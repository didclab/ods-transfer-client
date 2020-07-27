package com.ods.connector.FileShare.Model;

import java.util.List;


public class Directory {
	private String name;
    private String location;
    private String type;
	private List<Directory> directories;
	
	public Directory() {
	}

    public Directory(String name, String location, String type, List<Directory> dirList) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.directories = dirList;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Directory> getDirectoryList() {
        return this.directories;
    }

    public void setNodeList(List<Directory> dirList) {
        this.directories = dirList;
    }
}
