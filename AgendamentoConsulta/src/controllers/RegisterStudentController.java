/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static app.Program.*;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.JSONObject;
import static app.Program.socket;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import screens.RegisterScreen;

/**
 * FXML Controller class
 *
 * @author Maycon
 */
public class RegisterStudentController extends RegisterScreen{

    public RegisterStudentController() {
        initComponents();
        continuationInitComponents();
    }

    private void continuationInitComponents(){
        bSingUp.addActionListener((ActionEvent evt) -> {
            try {
                hundleRegistrar();
            } catch (IOException ex) {
                Logger.getLogger(ConnectionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
        bVoltar.addActionListener((ActionEvent evt) -> {
            hundleExit();
        }); 
   }

    @SuppressWarnings("unchecked")
    private void hundleRegistrar() throws IOException{
        try {
            // Read from interface
            String name = tfName.getText();
            String user = tfUser.getText();
            String pass = tfPass.getText();

            JSONObject route = new JSONObject();
            route.put("rota", "login.registro");
            route.put("nome", name);
            route.put("usuario", user);
            route.put("senha", pass);
            route.put("is_admin", false);
            route.put("is_monitor", false);

            // Shows what will be sent
            clientController.showSend(route.toString());
            // Send
            PrintWriter pr = new PrintWriter(socket.getOutputStream());  
            pr.println(route);
            pr.flush();
            
        }catch(Error e){
            
        }
    }
    
    private void hundleExit(){
        clientController.pContentClear();
        clientController.pContentAdd("login");
    }
}
