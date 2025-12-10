package com.library;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LibraryProject {
    public static void main(String[] args) {
    File file =new File("D:\\Books.txt");
     String []books=readTextFile(file);
     for (int i=0;i<books.length;i++)
        System.out.println(books[i]);
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
