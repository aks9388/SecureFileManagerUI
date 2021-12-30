package com.aks.securefilemanager.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.web.HTMLEditor;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private HTMLEditor htmlEditor;
    @FXML
    private TreeView treeView;

    private String filePath = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> rootItem = new TreeItem<>("Files");
        TreeItem<String> dummyItem = new TreeItem<>("Folder1");
        TreeItem<String> dummyItem1 = new TreeItem<>("File1");
        dummyItem.getChildren().add(dummyItem1);
        rootItem.getChildren().add(dummyItem);
        treeView.setRoot(rootItem);

    }
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
    @FXML
    protected void onSelectItem(){
        TreeItem<String> selectedItem = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();

        if(selectedItem != null){
            System.out.println(selectedItem.getValue() +" selected");
        }
    }
}