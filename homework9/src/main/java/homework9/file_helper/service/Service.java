package homework9.file_helper.service;

import homework9.file_helper.dao.Dao;

public class Service {
    private final Dao dao;

    public Service() {
        dao = new Dao();
    }

    public void listFilesAndDirectories(String directoryPath) {
        dao.listFilesAndDirectories(directoryPath);
    }

    public void createFile(String filePath) {
        dao.createFile(filePath);
    }

    public void createDirectory(String directoryPath) {
        dao.createDirectory(directoryPath);
    }

    public void deleteFile(String filePath) {
        dao.deleteFile(filePath);
    }

    public void deleteDirectory(String directoryPath) {
        dao.deleteDirectory(directoryPath);
    }

    public void moveFileOrDirectory(String sourcePath, String destinationPath) {
        dao.moveFileOrDirectory(sourcePath, destinationPath);
    }

    public void searchFileOrDirectory(String directoryPath, String name) {
        dao.searchFileOrDirectory(directoryPath, name);
    }

    public void searchFileContents(String directoryPath, String searchText) {
        dao.searchFileContents(directoryPath, searchText);
    }
}