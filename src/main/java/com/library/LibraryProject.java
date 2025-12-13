package com.library;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LibraryProject extends Application {
    File file;
    String []books=new String[0];
    Boolean fileUploaded=false;
     @Override
    public void start(Stage stage) {
        Button btn = new Button("Upload books");
        VBox root = new VBox();
        root.getChildren().add(btn);
        
        Button displayAllBooks = new Button("Display all books");
        Button addBooksButton = new Button("Add a new book");
        Button removeBooksButton = new Button("Remove a book");
        Button countTotalBooks = new Button("Click to count the books");
        TextField searchForBook = new TextField("Search for a book");
        Button searchButton = new Button("Search");
        Label label = new Label();
        Button saveAndExitButton = new Button("Save and Exit");
        
        stage.setScene(new Scene(root, 400, 300));
        stage.setTitle("FCDS Library Project");
        stage.show();
        
        btn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                System.out.println("File selected: " + selectedFile.getAbsolutePath());
                file = selectedFile;
                root.getChildren().remove(btn);
                books = readTextFile(file);
                for (String book : books) {
                    System.out.println(book);
                }
                //System.out.println(books);
                books=removeFromBooks(books,null);// Remove count from books array
                fileUploaded = true;
                // Add buttons AFTER file is uploaded
                root.getChildren().addAll(displayAllBooks, addBooksButton, removeBooksButton, countTotalBooks, searchForBook, label, searchButton, saveAndExitButton);
            } else {
                System.out.println("File selection cancelled.");
            }
        });
        
        displayAllBooks.setOnAction(e -> {
            String allBooks = "";
            for (int i = 0; i < books.length - 1; i++) {
                allBooks += (i + 1) + ". " + books[i] + "\n";
            }
            label.setText(allBooks);
        });
        addBooksButton.setOnAction(e -> {
            TextField newBookField = new TextField("Enter new book name");
            Button confirmAddButton = new Button("Add Book");
            root.getChildren().addAll(newBookField, confirmAddButton);
            confirmAddButton.setOnAction(ev -> {
                String newBook = newBookField.getText();
                books = addToBooks(books, newBook);
                root.getChildren().removeAll(newBookField, confirmAddButton);
                label.setText("Book added: " + newBook);
            });
        });
        removeBooksButton.setOnAction(e -> {
            TextField removeBookField = new TextField("Enter book name to remove");
            Button confirmRemoveButton = new Button("Remove Book");
            root.getChildren().addAll(removeBookField, confirmRemoveButton);
            confirmRemoveButton.setOnAction(ev -> {
                String bookToRemove = removeBookField.getText();
                String[] updatedBooks = new String[books.length - 1];
                int index = 0;
                boolean found = false;
                for (String book : books) {
                    if (book.equals(bookToRemove) && !found) {
                        found = true;
                        continue;
                    }
                    if (index < updatedBooks.length) {
                        updatedBooks[index++] = book;
                    }
                }
                if (found) {
                    books = updatedBooks;
                    label.setText("Book removed: " + bookToRemove);
                } else {
                    label.setText("Book not found: " + bookToRemove);
                }
                root.getChildren().removeAll(removeBookField, confirmRemoveButton);
            });
        });
        countTotalBooks.setOnAction(e -> {
            label.setText("Total books: " + (books.length - 1));
        });
        searchButton.setOnAction(e -> {
            String searchQuery = searchForBook.getText().toLowerCase();
            String foundBooks = "";
            for (String book : books) {
                if (book.toLowerCase().contains(searchQuery)) {
                    foundBooks += book + "\n";
                }
            }
            if (foundBooks.isEmpty()) {
                label.setText("No books found for: " + searchQuery);
            } else {
                label.setText("Books found:\n" + foundBooks);
            }
        });
        saveAndExitButton.setOnAction(e -> {
            saveAndExit(file, books);
            stage.close();
        });
    }

    public static void main(String[] args) {
    launch(args);
    }




    private String[] addToBooks(String[] books, String book) {
    String[] newBooks=new String[books.length+1];
    for (int i=0;i<books.length;i++){
        newBooks[i]=books[i];
    }        
    newBooks[books.length]=book;
    return newBooks;
    }
    private String[] removeFromBooks(String[] books, String book) {
    String[] newBooks=new String[books.length-1];
    for (int i=0;i<newBooks.length;i++){
        
        newBooks[i]=books[i];
    }
    return newBooks;
    }


    private static String[] readTextFile(File file) {
        int i=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("---- File Content ----");
            while ((line = reader.readLine()) != null) {
                i++;
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Failed");
        }
        String arr[]=new String[i+1];
        i=0;
        try (BufferedReader reader=new BufferedReader(new FileReader(file))){
            String line;
            while((line=reader.readLine())!=null){
                arr[i]=line;
                i++;
                //System.out.println(arr[i]);
            }
            arr[i]=String.valueOf(i);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Failed");
        }
        return arr;
    }
    public void saveAndExit(File file,String[] books){ 
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(String.join("\n", books));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
