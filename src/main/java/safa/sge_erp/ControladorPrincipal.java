package safa.sge_erp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
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
    private Button btnCrear;

    @FXML
    private Button btnRegistro;

    @FXML
    private GridPane gpBasesDeDatos;

    @FXML
    private Label labelBienvenido;

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

    // Atributos
    int counter = 0;

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

    /* PANEL BASES DE DATOS */
    @FXML
    void crearBD(ActionEvent event) {
        nuevaFila();
        gpBasesDeDatos.setVgap(10);
    }

    void nuevaFila() {
        Label label = new Label("Label " + counter);
        Button button1 = new Button("Button 1 " + counter);
        Button button2 = new Button("Button 2 " + counter);

        EventHandler<ActionEvent> eventHandler = e -> {
            Button b = (Button) e.getSource();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Button Information");
            alert.setHeaderText(null);
            alert.setContentText(b.getText());
            alert.showAndWait();
        };

        button1.setOnAction(eventHandler);
        button2.setOnAction(eventHandler);

        HBox hbox = new HBox(label, button1, button2);
        hbox.setSpacing(400);
        hbox.setAlignment(Pos.CENTER);

        StackPane sp = new StackPane(hbox);
        sp.setPadding(new Insets(50));
        sp.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(30), BorderWidths.DEFAULT)));
        sp.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(30), Insets.EMPTY)));

        gpBasesDeDatos.addRow(counter + 1, sp);
        GridPane.setHalignment(sp, HPos.CENTER);
        GridPane.setFillWidth(sp, true);
        counter++;
    }

    /* INICIALIZACIÓN */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelBienvenido.setText(labelBienvenido.getText()+"Paco");
        gpBasesDeDatos.setVgap(0);
    }

}
