/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static app.Program.socket;

/**
 * FXML Controller class
 *
 * @author gui_o
 */
public class LogController implements Initializable {

    @FXML
    private TextField tfIP;
    
    @FXML
    private TextField tfPort;
    
    @FXML
    private Button btnConection;
    
    @FXML
    private void hundleConection(ActionEvent event) throws IOException{
        
        // Read from interface
        String ip = tfIP.getText();
        String port = tfPort.getText();

        conection(ip, Integer.valueOf(port));
        
    }
    
    private void conection(String ip, int port) throws IOException{
        try {
            socket = new Socket(ip, port);
            if(socket!=null){
                System.out.println("Cliente conectado em: "+ ip + " porta: " + port);
            }
            File fLog = new File("log.txt"); 
            fLog.createNewFile();
            /*FileWriter fwLog = new FileWriter(fLog); 
            fwLog.write("conectado\n");
            fwLog.flush();
            fwLog.close();*/
        } catch (IOException ex) {
            int answerDialog = JOptionPane.showConfirmDialog(null, "Try to connect again", "Error Server", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            //if(answerDialog == JOptionPane.YES_OPTION)
               //conection(ip, port);
             System.exit(0);
        } finally{
            // Opens screen
            Pane root = FXMLLoader.load(getClass().getResource("../Screens/Login.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            // Close Screen
            Stage stage = (Stage) btnConection.getScene().getWindow();
            stage.close();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}