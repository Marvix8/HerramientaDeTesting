package edu.unlam.herramienta_testing;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Directory {
	
	private ArrayList<String> filesList;
	
	public Directory(String path) {
		
		this.filesList = new ArrayList<String>();
		
		searchFilesIntoPath(path);
	}

	private void searchFilesIntoPath(String path) {
		
		File dir = new File(path);
		
		for (String fileOrDirectoryName: dir.list()) {
			
			String subPath = path + "/" + fileOrDirectoryName;
			
			File subDir = new File(subPath);
			
			if(subDir.isDirectory()) {
				
				searchFilesIntoPath(subPath);
				
			}
			else if(fileOrDirectoryName.endsWith(".java")){
				
				this.filesList.add(fileOrDirectoryName);
			}
		}		
	}

	public ArrayList<String> getFilesList() {
		return filesList;
	}

	public void setFilesList(ArrayList<String> filesList) {
		this.filesList = filesList;
	}

}
