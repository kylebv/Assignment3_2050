package models;

import java.io.File;

/**
 * Created by kyleb on 2/06/2019.
 */
public class FileModel {
    File fileData;
    String fileExtension;

    public FileModel(File fileData, String fileExtension) {
        this.fileData = fileData;
        this.fileExtension = fileExtension;
    }

    public FileModel() {
    }

    public File getFileData() {
        return fileData;
    }

    public void setFileData(File fileData) {
        this.fileData = fileData;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
