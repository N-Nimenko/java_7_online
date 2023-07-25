package homework9.file_helper.controller;

import homework9.file_helper.service.Service;
import homework9.file_helper.util.AppUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    public void start(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to file helper application");
        System.out.println("Select your option");
        String answer;
        menu();
        try{
            while((answer = bufferedReader.readLine()) != null) {
                crud(bufferedReader, answer);
            }
        } catch (IOException e){
            System.err.println("An error happened: " + e.getMessage());
        }
    }

    public void menu(){
        System.out.println("***********************************************************");
        System.out.println("If you want to see a list of all files and folders in the specified directory, enter 1");
        System.out.println("If you want to create a file or folder in the specified directory, enter 2");
        System.out.println("If you want to delete a file or folder in the specified directory, enter 3");
        System.out.println("If you want to move a file or folder from the one specified directory to the second specified directory, enter 4");
        System.out.println("If you want to search for a file or folder in the specified directory, enter 5");
        System.out.println("If you want to search a text in all files and folders in the specified directory, enter 6");
        System.out.println("If you want to close the application, enter 0");
        System.out.println("***********************************************************");
    }

    public void crud(BufferedReader bufferedReader, String answer){
        try {
            switch(answer){
                case "1" -> listFilesAndDirectories(bufferedReader);
                case "2" -> createFileOrDirectory(bufferedReader);
                case "3" -> deleteFileOrDirectory(bufferedReader);
                case "4" -> moveFileOrDirectory(bufferedReader);
                case "5" -> searchFileOrDirectory(bufferedReader);
                case "6" -> searchFileContents(bufferedReader);
                case "0" -> System.exit(0);
            }
        } catch(NumberFormatException e){
            System.err.println("An error occurred: " + e.getMessage());
        }
        menu();
    }

    public void listFilesAndDirectories(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FILE_PATH);
            String directoryPath = bufferedReader.readLine();

            Service service = new Service();
            service.listFilesAndDirectories(directoryPath);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void createFileOrDirectory(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FILE_PATH);
            String createDirectoryPath = bufferedReader.readLine();
            System.out.println(AppUtil.FILE_NAME);
            String createName = bufferedReader.readLine();
            System.out.println("Choose an option:");
            System.out.println("1. Create a file");
            System.out.println("2. Create a directory");
            String createOption = bufferedReader.readLine();

            Service service = new Service();

            switch (createOption) {
                case "1" -> service.createFile(createDirectoryPath + File.separator + createName);
                case "2" -> service.createDirectory(createDirectoryPath + File.separator + createName);
                default -> System.out.println("Invalid input. Please try again.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void deleteFileOrDirectory(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FILE_PATH);
            String directoryPath = bufferedReader.readLine();
            System.out.println(AppUtil.FILE_NAME);
            String name = bufferedReader.readLine();
            System.out.println("Choose an option:");
            System.out.println("1. Delete a file");
            System.out.println("2. Delete a directory");
            String option = bufferedReader.readLine();

            Service service = new Service();

            switch (option) {
                case "1" -> service.deleteFile(directoryPath + File.separator + name);
                case "2" -> service.deleteDirectory(directoryPath + File.separator + name);
                default -> System.out.println("Invalid input. Please try again.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void moveFileOrDirectory(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FILE_PATH);
            String sourceDirectoryPath = bufferedReader.readLine();
            System.out.println("Enter the destination directory path:");
            String destinationDirectoryPath = bufferedReader.readLine();
            System.out.println("Enter the name of the file or directory to move:");
            String name = bufferedReader.readLine();

            Service service = new Service();
            service.moveFileOrDirectory(sourceDirectoryPath + File.separator + name, destinationDirectoryPath);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void searchFileOrDirectory(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FILE_PATH);
            String directoryPath = bufferedReader.readLine();
            System.out.println("Enter the name of the file or directory to search:");
            String name = bufferedReader.readLine();

            Service service = new Service();
            service.searchFileOrDirectory(directoryPath, name);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void searchFileContents(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FILE_PATH);
            String directoryPath = bufferedReader.readLine();
            System.out.println("Enter the text to search:");
            String searchText = bufferedReader.readLine();

            Service service = new Service();
            service.searchFileContents(directoryPath, searchText);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}


