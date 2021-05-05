package dice;

import entites.Route;
import static app.Program.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import org.json.simple.JSONObject;

/**
 *
 * @author gui_o
 */
public class ManagerClient extends Thread{
    private InputStreamReader in;
    private OutputStreamWriter ou;
    private PrintWriter pr;
    private BufferedReader bf;
    private Color legend;
    private boolean bRoute;
    
    public void startReceived() throws IOException{
        in = new InputStreamReader(socket.getInputStream());
        ou = new OutputStreamWriter(socket.getOutputStream());
        bf = new BufferedReader(in);
        
        // Opens screen Login
        clientController.pContentAdd("login");

        this.start();
    }
    
    @Override
    public void run(){
        try { // an echo server
            while (true) {
                
                //Read String
                String sRoute = bf.readLine();
                
                // Convert Json String to Route Object
                Gson gson = new Gson();
                Route rRoute = gson.fromJson(sRoute, Route.class);
                
                if("false".equals(rRoute.getErro()))
                {
                    bRoute = true;
                    legend = Color.GREEN;
                }
                else{ 
                    bRoute = false;
                    legend = Color.YELLOW;
                }
                
                switch (rRoute.getRota()) {
                    case "login.login" ->
                    {    
                        clientController.showReceived(sRoute, legend);
                        login(rRoute.getTipo_usuario());
                    }
                    case "login.logout" ->
                    {
                        clientController.showReceived(sRoute, legend);
                        logout();
                    }
                    case "login.registro" ->
                    {
                        clientController.showReceived(sRoute, legend);
                        register();
                    }
                    case "login.update" -> 
                    {
                        clientController.showReceived(sRoute, legend);
                        update();
                    }
                    case "usuario.delete" ->
                    {
                        clientController.showReceived(sRoute, legend);
                        delete();
                    }
                    case "cliente.usuarios-ativos" -> users(sRoute);
                    case "mensagem.mesagem" -> 
                    {
                        clientController.showReceived(sRoute, legend);
                        mensagem();
                    }
                    case "monitor.listar" ->{
                        clientController.showReceived(sRoute, legend);
                        monitores();
                    }
                    default -> 
                    {
                        clientController.showReceived(sRoute, Color.RED);
                        errorRoute();
                    }
                }
            }
        } catch(EOFException e) {
             System.out.println("EOF:"+e.getMessage());
        } catch(IOException e) {
             System.out.println("IO:"+e.getMessage());
        } catch(JsonSyntaxException e){
            System.out.println(e);
        }
        finally{ 
           try {
               socket.close();
           }catch (IOException e){
               /*close failed*/
           }
        }
    } 

    @SuppressWarnings("unchecked")
    private void login(String sTypeUser) throws IOException {

        //Valid Login
        if(!bRoute) return;
        
        clientController.pContentClear();
        switch(sTypeUser){
            case "admin"    -> 
            {
                admin = true;
                clientController.pContentAdd("registerMonitor");
                clientController.pContentAdd("managementMonitor");
                
                JSONObject route = new JSONObject();
                //route.put("rota", "monitor.listar");
                
                //clientController.showSend(route.toString());
                
                // Send
                //pr = new PrintWriter(ou);  
                //pr.println(route);
                //pr.flush();
                
            }
            //case "monitor"  ->
            //case "aluno"    ->
        }
            //clientController.pContentAdd("login");
        
    }

    private void logout() throws IOException {
        
        clientController.pContentClear();
        clientController.pContentAdd("login");
    }

    private void register() throws IOException {

        if(!bRoute) return;
        if (!admin){
            clientController.pContentClear();
            clientController.pContentAdd("login");
        }

    }

    private void update() throws IOException {
        
    }

    private void delete() throws IOException {

        if(bRoute){
          //  root =  FXMLLoader.load(getClass().getResource("../screens/Login.fxml"));
            //usuario = gson.fromJson(sRoute, User.class);
        }

    }
    
    private void monitores(){
        managementMonitorController.cbMonitor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    }

    private void users(String sRoute) {
        System.out.println("Received <- "+sRoute);
    }

    private void mensagem() {

    }

    private void errorRoute() {
        JOptionPane.showMessageDialog(
            null, 
            "No route found", 
            "Route error", 
            JOptionPane.ERROR_MESSAGE
        );
    }
    
}
