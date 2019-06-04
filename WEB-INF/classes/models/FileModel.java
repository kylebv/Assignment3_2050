package models;

import java.io.File;

/**
 * Created by kyleb on 2/06/2019.
 */
public class FileModel {
    int fileID;
    String fileName;

    public FileModel(int fileID, String fileName) {
        this.fileID = fileID;
        this.fileName = fileName;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileModel() {
    }


}
