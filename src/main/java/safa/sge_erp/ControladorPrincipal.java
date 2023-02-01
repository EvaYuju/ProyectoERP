package safa.sge_erp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {

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

    // Métodos
    /* PANEL USUARIOS */
    @FXML
    void acceder(ActionEvent event) {

    }

    @FXML
    void aceptaRegistro(ActionEvent event) {

    }

    @FXML
    void cancelaRegistro(ActionEvent event) {

    }

    @FXML
    void nuevoRegistro(ActionEvent event) {

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
