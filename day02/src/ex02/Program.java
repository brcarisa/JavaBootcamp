package ex02;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    private static final String PATH = "--current-folder=";
    private static File currentFolder;

    public static void main(String[] args) {
        if(args.length != 1) {
            System.err.println("Please provide a path to the current folder");
            System.exit(-1);
        }
        if(!args[0].startsWith(PATH)){
            System.err.println("Please use the term --current-folder=<path>");
            System.exit(-1);
        }
        String inputPath = args[0].substring(PATH.length());
        if(inputPath.isEmpty()){
            System.err.println("Please write the current folder");
            System.exit(-1);
        }
        currentFolder = new File(inputPath);
        if(!currentFolder.isDirectory()){
            System.err.println("This is not a folder");
            System.exit(-1);
        }
        System.out.println(currentFolder.getAbsolutePath());
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("exit")){
            if(!input.isEmpty()){
                run(input);
            }
            input = scanner.nextLine();
        }
        scanner.close();
    }

    private static void run(String input){
        String[] commandLine = input.split("\\s+");
        if(commandLine[0].equals("ls")){
            if(commandLine.length == 1){
                executeLsCommand();
            } else {
                System.out.println("Unknown command: " + input);
            }
        } else if(commandLine[0].equals("mv")){
            if(commandLine.length == 3){
                executeMvCommand(commandLine);
            } else {
                System.out.println("Unknown command: " + input);
            }
        } else if(commandLine[0].equals("cd")){
            if(commandLine.length == 2){
                executeCdCommand(commandLine);
            } else {
                System.out.println("Unknown command: " + input);
            }
        } else {
            System.out.println("Unknown command: " + input);
        }
    }

    private static void executeLsCommand(){
        File[] files = currentFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName() + " - " + file.length()/1024 + " KB");
            }
        } else {
            System.out.println("Директория пуста");
        }
    }

    private static void executeMvCommand(String[] commandLine){
        String inputPath = currentFolder.getAbsolutePath() + File.separator + commandLine[1];
        String outputPath = currentFolder.getAbsolutePath() + File.separator + commandLine[2];
        File fileToMove = new File(inputPath);
        if(!fileToMove.exists()){
            System.out.println("File " + inputPath + " does not exist");
            return;
        }
        File outputFile = new File(outputPath);
        if(outputFile.isDirectory()){
            outputFile = new File(outputPath + File.separator + fileToMove.getName());
        }
        boolean isMoved = fileToMove.renameTo(outputFile);
        if(!isMoved){
            System.out.println("File " + outputPath + " already exists");
        }
    }

    private static void executeCdCommand(String[] commandLine) {
        File inputFile = new File(currentFolder.getAbsolutePath() + File.separator + commandLine[1]);
        if(!inputFile.isDirectory()){
            System.out.println("This is not a folder");
            return;
        }
        if(!inputFile.exists()){
            System.out.println(inputFile + " No such file or directory");
        } else {
            currentFolder = inputFile;
            try {
                currentFolder = new File(currentFolder.getCanonicalPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(currentFolder.getAbsolutePath());
        }
    }
}
