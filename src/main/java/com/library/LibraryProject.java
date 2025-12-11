package com.library;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LibraryProject extends Application {
     @Override
    public void start(Stage stage) {
        Button btn = new Button("Hello JavaFX!");
        StackPane root = new StackPane(btn);

        stage.setScene(new Scene(root, 400, 300));
        stage.setTitle("My GUI");
        stage.show();
    }

    public static void main(String[] args) {
    File file =new File("");
     String []books=readTextFile(file);
    
    }

    private void addToBooks(String[] books, String book) {
        

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
