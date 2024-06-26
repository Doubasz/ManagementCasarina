package org.example.casarinagestion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WelcomeController {
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private TextField idClientInput;
    @FXML
    Label errorMsg;
    int idClient;

    @FXML
    public void onClick(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userScene.fxml"));
        root = loader.load();

        UserController temp = loader.getController();

        idClient = 0;
        try {
            idClient = Integer.parseInt(idClientInput.getText());
        }catch (Exception ignored){}

        try{
            String connectionUrl = "jdbc:mysql://localhost:3306/casarina";
            Connection con = DriverManager.getConnection(connectionUrl, "ferhat", "0000");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Client");
            while(rs.next()){
                int id = rs.getInt("idClient");
                if(idClient == id){
                    temp.changeValue(idClient, rs.getString("Nom"), rs.getString("Prénom"), rs.getInt("NbPresence"));
                    errorMsg.setVisible(false);
                    switchMainScene(root, e, "userScene.fxml");
                    break;
                }
                else errorMsg.setVisible(true);
            }
            stmt.close();
        }
        catch(SQLException ex){
            Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void switchMainScene(Parent root1, ActionEvent e, String sceneName) throws IOException {
        errorMsg.setVisible(false);
        root = root1;
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}