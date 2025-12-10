/*package com.library;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.FileUtils;

import java.io.File;

public class MainControllerUI {
    @FXML
    public void uploadFile() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select a Text File");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        File file = chooser.showOpenDialog(new Stage());

        if (file != null) {
            String content = FileUtils.readTextFile(file);
            System.out.println(content);   // or display in a TextArea
        }
    }
}*/


