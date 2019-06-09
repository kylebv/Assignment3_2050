/* System component model
Seng2050 -  Web Engineering (Assignment 3)
Created by: Kyle Vincent
Modified by : Angus Simmons, Jeff Layton, Kyle Vincent, Sam Williams
Last edit: 9/6/19 */
package models;
/////////////////////////////////LIBRARY IMPORTS///////////////////////
import java.io.File;
/////////////////////////////USER DEFINED CLASS///////////////////////   
public class FileModel {
/////////////////////////////////DECLARATIONS///////////////////////////    
	int fileID;
    String fileName;
	public FileModel() {
    }
   public FileModel(int fileID, String fileName) {
        this.fileID = fileID;
        this.fileName = fileName;
    }
/////////////////////////////////GETTERS///////////////////////////////////
    public int getFileID() {
        return fileID;
    }
	public String getFileName() {
        return fileName;
    }
/////////////////////////////////SETTERS///////////////////////////////////	
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
	 public void setFileID(int fileID) {
        this.fileID = fileID;
    }
}