package com.aks.securefilemanager.Controller;

import javafx.fxml.FXML;
import javafx.scene.web.HTMLEditor;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MainController {

    @FXML
    private HTMLEditor htmlEditor;
    private String filePath = null;
    @FXML
    protected void saveFile(){
        if(filePath==null) {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(new Stage());

            if(selectedDirectory == null){
                //No Directory selected
            }else{
                String folderPath = selectedDirectory.getAbsolutePath();
                String fileName = "Test";
                filePath = folderPath+"\\"+fileName;
                System.out.println(filePath);
            }
        }

        String text = htmlEditor.getHtmlText();
        byte[] data = text.getBytes(StandardCharsets.UTF_8);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            out.write(data);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(text);
    }
    @FXML
    protected void openFile() throws IOException {
        filePath = "C:\\Users\\ABHI\\Documents\\Test";
        if(filePath==null){
            System.out.println("No File path Provided");
        }
        FileInputStream is = null;
        try {
            is = new FileInputStream(filePath);
            byte[] data = is.readAllBytes();
            is.close();

            htmlEditor.setHtmlText(new String(data, StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }

    }
}