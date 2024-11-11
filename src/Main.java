import java.io.*;
import java.util.Scanner;

// if you want to see files getting created or deleted, you have to press "ctr + alt + Y" after methods have been called

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int selectedMenu;
        String fileName;

        while (!exit) {
            System.out.println("Welcome to File Control");
            MenuMessage();
            selectedMenu = scanner.nextInt();

            switch (selectedMenu) {
                case (1):
                    System.out.println("This is the Create File menu");
                    System.out.println("Please enter a name for the file you want to create");
                    fileName = nextFile(scanner);
                    createFile(fileName);
                    break;
                case (2):
                    System.out.println("This is the Read From File menu");
                    fileName = nextFile(scanner);
                    readFromFile(fileName);
                    break;
                case (3):
                    System.out.println("This is the Write to File menu");
                    fileName = nextFile(scanner);
                    System.out.println("Please enter the content you to write");
                    String content = scanner.nextLine();
                    writeToFile(fileName, content);
                    break;
                case (4):
                    System.out.println("This is the Delete File menu");
                    fileName = nextFile(scanner);
                    deleteFile(fileName);
                    break;
                case (5):
                    exit = true;
                    System.out.println("Shutting down program...");
                default:
                    System.out.println("Wrong input, please try again");
                    break;
            }
        }

        scanner.close();
    }// main End

    public static void MenuMessage() {
        System.out.println("You have the following options:");
        System.out.println("Option 1: Create File");
        System.out.println("Option 2: Read File");
        System.out.println("Option 3: Write File");
        System.out.println("Option 4: Delete File");
        System.out.println("Option 5: Exit Program");
    }

    public static String nextFile(Scanner scanner) {
        scanner.nextLine();
        System.out.println("please enter your filename");
        return scanner.nextLine();
    }

    public static void createFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File " + fileName + " has been created");
            } else {
                System.out.println("File already exists");
            }
        }
        catch (IOException e) {
            System.out.println("Error: something wnt wrong when creating a file: " + e.getMessage());
        }
    }

    public static void readFromFile(String fileName) {
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(reader);
            String line;
            System.out.println("This is the content of file: " + fileName);
            while ((line = br.readLine()) != null) {
                System.out.println(line);

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        } catch (IOException ioe) {
            System.out.println("Error: io exception ");
        }
    }

    public static void writeToFile(String filename, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))){
            bw.write(content);
            bw.newLine();
            System.out.println("Content has been written to file: " + filename);
        }
        catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }

    }

    public static void deleteFile(String fileName){
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("File: " + fileName + " has been deleted");
        }
        else {
            System.out.println("File could not be deleted, check if file exists");
        }
    }



}// Main End