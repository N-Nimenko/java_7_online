package homework9.file_helper.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHelperStorage {
    public void createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void createDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.mkdir()) {
            System.out.println("Directory created successfully.");
        } else {
            System.out.println("Directory already exists.");
        }
    }

    public void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.delete()) {
            System.out.println("File deleted successfully.");
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public void deleteDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            deleteDirectoryRecursively(directory);
            System.out.println("Directory deleted successfully.");
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }
    }

    private void deleteDirectoryRecursively(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectoryRecursively(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }

    public void moveFileOrDirectory(String sourcePath, String destinationPath) {
        File source = new File(sourcePath);
        File destination = new File(destinationPath, source.getName());
        if (source.renameTo(destination)) {
            System.out.println("File or directory moved successfully.");
        } else {
            System.out.println("Failed to move the file or directory.");
        }
    }

    public void searchFileOrDirectory(String directoryPath, String name) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            searchFileOrDirectoryRecursive(directory, name);
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }
    }

    private void searchFileOrDirectoryRecursive(File directory, String name) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(name)) {
                    System.out.println("Found: " + file.getAbsolutePath());
                }
                if (file.isDirectory()) {
                    searchFileOrDirectoryRecursive(file, name);
                }
            }
        }
    }

    public void searchFileContents(String directoryPath, String searchText) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            searchFileContentsRecursive(directory, searchText);
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }
    }

    private void searchFileContentsRecursive(File directory, String searchText) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    searchFileContentsInFile(file, searchText);
                } else if (file.isDirectory()) {
                    searchFileContentsRecursive(file, searchText);
                }
            }
        }
    }

    private void searchFileContentsInFile(File file, String searchText) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.contains(searchText)) {
                    System.out.println("Found at: " + file.getAbsolutePath() + ", Line: " + lineNumber + ", Content: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public void listFilesAndDirectories(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            listFilesAndDirectoriesRecursive(directory, "");
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }
    }

    private void listFilesAndDirectoriesRecursive(File directory, String indent) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(indent + file.getName());
                if (file.isDirectory()) {
                    listFilesAndDirectoriesRecursive(file, indent + "  ");
                }
            }
        }
    }
}



