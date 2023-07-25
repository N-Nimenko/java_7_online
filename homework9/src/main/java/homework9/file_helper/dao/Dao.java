package homework9.file_helper.dao;

import homework9.file_helper.db.FileHelperStorage;

public class Dao {
    private final FileHelperStorage fileHelperStorage;

    public Dao() {
        fileHelperStorage = new FileHelperStorage();
    }

    public void listFilesAndDirectories(String directoryPath) {
        fileHelperStorage.listFilesAndDirectories(directoryPath);
    }

    public void createFile(String filePath) {
        fileHelperStorage.createFile(filePath);
    }

    public void createDirectory(String directoryPath) {
        fileHelperStorage.createDirectory(directoryPath);
    }

    public void deleteFile(String filePath) {
        fileHelperStorage.deleteFile(filePath);
    }

    public void deleteDirectory(String directoryPath) {
        fileHelperStorage.deleteDirectory(directoryPath);
    }

    public void moveFileOrDirectory(String sourcePath, String destinationPath) {
        fileHelperStorage.moveFileOrDirectory(sourcePath, destinationPath);
    }

    public void searchFileOrDirectory(String directoryPath, String name) {
        fileHelperStorage.searchFileOrDirectory(directoryPath, name);
    }

    public void searchFileContents(String directoryPath, String searchText) {
        fileHelperStorage.searchFileContents(directoryPath, searchText);
    }
}