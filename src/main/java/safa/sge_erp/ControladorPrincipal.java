package safa.sge_erp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {

    //public static FXMLLoader fxmlLoader;
    private FXMLLoader fxmlLoader;
    @FXML
    private Button btnAcceder;

    @FXML
    private Button btnAceptarRegistro;

    @FXML
    private Button btnCancelarRegistro;

    @FXML
    private Button btnRegistro;

    @FXML
    private AnchorPane panelBD;

    @FXML
    private AnchorPane panelLogin;

    @FXML
    private AnchorPane panelRegistro;

    @FXML
    private AnchorPane panelUsuario;

    @FXML
    private TextField tfLoginClave;

    @FXML
    private TextField tfLoginUsuario;

    @FXML
    private TextField tfRegistroClave;

    @FXML
    private TextField tfRegistroConfirmaClave;

    @FXML
    private TextField tfRegistroEmail;

    @FXML
    private TextField tfRegistroUsuario;

    // Base de datos
    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;

    public Connection conectarBD() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/usuarios_erp", "root", "root");
            return  connect;
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }

    // Métodos
    // LOGIN USUARIO
    @FXML
    public void acceder(ActionEvent event) {
        connect = conectarBD();
        //Parent root = fxmlLoader.getRoot();

        try {

            String sql = "SELECT * FROM usuarios WHERE nombre=? AND clave = ?";

            statement = connect.prepareStatement(sql);
            statement.setString(1, tfLoginUsuario.getText());
            statement.setString(2, tfLoginClave.getText());
            result = statement.executeQuery();

            if (result.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Mensaje de información");
                alert.setContentText("Login correcto");

                alert.showAndWait();
                 // Carga el panel BD
                panelLogin.setVisible(false);
                panelBD.setVisible(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Mensaje de información");
                alert.setContentText("Usuario/Clave incorrectos");

                alert.showAndWait();

            }

        }catch (Exception e) {e.printStackTrace();}
    }
    // REGISTRO USUARIO
    @FXML
    void aceptaRegistro(ActionEvent event) {
        connect = conectarBD();

        try {
            String sql ="INSERT INTO usuarios VALUES(?,?,?)";

            statement = conectarBD().prepareStatement(sql);
            statement.setString(1,tfRegistroUsuario.getText());
            statement.setString(2,tfRegistroEmail.getText());
            statement.setString(3,tfRegistroClave.getText());
            statement.setString(3,tfRegistroConfirmaClave.getText());
            statement.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText("Mensaje de información");
            alert.setContentText("USUARIO INSERTADO");

            panelRegistro.setVisible(false);
            panelLogin.setVisible(true);


        }catch(Exception e) {e.printStackTrace();}
    }


    @FXML
    void nuevoRegistro(ActionEvent event) {
        panelLogin.setVisible(false);
        panelRegistro.setVisible(true);

    }

    public void cleanFields(){
        tfLoginClave.setText("");
        tfLoginUsuario.setText("");
        tfRegistroEmail.setText("");
        tfRegistroClave.setText("");
        tfRegistroUsuario.setText("");
        tfRegistroConfirmaClave.setText("");
    }


    @FXML
    void cancelaRegistro(ActionEvent event) {
        tfRegistroEmail.setText("");
        tfRegistroClave.setText("");
        tfRegistroUsuario.setText("");
        tfRegistroConfirmaClave.setText("");
        panelRegistro.setVisible(false);
        panelBD.setVisible(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //panelLogin.setVisible(true);

    }


    public void setFxmlLoader(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }
}
