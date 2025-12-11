package com.library;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LibraryProject extends Application {
     @Override
    public void start(Stage stage) {
        Button btn = new Button("Upload books");
        StackPane root = new StackPane(btn);
        String[] filePath={""};
        btn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");

            // Optional: set extension filters
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
            );
             File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                System.out.println("File selected: " + selectedFile.getAbsolutePath());
                filePath[0]=selectedFile.getAbsolutePath();
                root.getChildren().remove(btn);
                // You can now read the file or store its path
            } else {
                System.out.println("File selection cancelled.");
            }
        });
        Button displayAllBooks=new Button("Display all books");
        Button addBooksButton=new Button("Add a new book");
        Button removeBooksButton=new Button("Remove a book");
        Button countTotalBooks=new Button("Click to count the books");
        TextField searchForBook=new TextField("Search for a book");
        File file=new File(filePath[0]);
     String []books=readTextFile(file);
        stage.setScene(new Scene(root, 400, 300));
        stage.setTitle("My GUI");
        stage.show();
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

}
