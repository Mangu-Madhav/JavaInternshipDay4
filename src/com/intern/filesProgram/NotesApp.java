package com.intern.filesProgram;

import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static String fileName;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask user for file name once
        System.out.print("Enter file name to use for notes (e.g., mynotes.txt): ");
        fileName = sc.nextLine();

        int choice;
        do {
            System.out.println("\n--- Notes App ---");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addNote(sc);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        } while (choice != 3);

        sc.close();
    }

    private static void addNote(Scanner scanner) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            System.out.print("Enter your note: ");
            String note = scanner.nextLine();
            writer.write(note + System.lineSeparator());
            System.out.println("Note added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void viewNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("\n--- Your Notes ---");
            boolean hasNotes = false;
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
                hasNotes = true;
            }
            if (!hasNotes) {
                System.out.println("(No notes found)");
            }
        } catch (FileNotFoundException e) {
            System.err.println("No notes found. Add some first!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}