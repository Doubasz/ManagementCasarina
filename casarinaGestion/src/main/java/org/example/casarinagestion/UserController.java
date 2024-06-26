package org.example.casarinagestion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController {
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    Label idLabel;
    @FXML
    Label nomLabel;
    @FXML
    Label prenomLabel;
    @FXML
    Label naissanceLabel;
    @FXML
    Label adresseLabel;
    @FXML
    Label presenceLabel;
    int idClient;

    public void changeValue(int id, String nom, String prenom, int presence){
        idClient = id;
        idLabel.setText(idLabel.getText() + " " + id);
        nomLabel.setText(nom);
        prenomLabel.setText(prenom);
        presenceLabel.setText(String.valueOf(presence));
    }

    @FXML
    public void retour(ActionEvent e) throws IOException {
        switchMainScene(e, "mainScene.fxml");
    }

    public void switchMainScene(ActionEvent e, String sceneName) throws IOException {
        root = FXMLLoader.load(getClass().getResource(sceneName));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ajoutPresence(){
        try{
            String connectionUrl = "jdbc:mysql://localhost:3306/casarina";
            Connection con = DriverManager.getConnection(connectionUrl, "ferhat", "0000");
            Statement stmt = con.createStatement();
            String qry = "UPDATE Client SET nbPresence = nbPresence + 1 WHERE idClient = " + idClient;
            stmt.execute(qry);
            stmt.close();
        }
        catch(SQLException ex){
            Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
