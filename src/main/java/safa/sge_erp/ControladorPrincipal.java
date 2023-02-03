package safa.sge_erp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.layout.*;

import static safa.sge_erp.ConexionBD.conexionBD;
import static safa.sge_erp.ConexionBD.conexionMySQL;


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
    Usuario usuario;
    int contadorFilas = 0;
    Connection bdSeleccionada;

    // Métodos
    /* PANEL USUARIOS */
    @FXML
    public void acceder(ActionEvent event) throws SQLException, ClassNotFoundException {
        Connection connection = conexionBD("usuarios_erp");
        String sql = "SELECT * FROM usuarios WHERE nombre=? AND clave = ?";
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setString(1, tfLoginUsuario.getText());
            statement.setString(2, tfLoginClave.getText());
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Mensaje de información");
                alert.setContentText("Login correcto");

                alert.showAndWait();

                connection.close();
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


    @FXML
    void aceptaRegistro(ActionEvent event) throws SQLException, ClassNotFoundException {
        Connection connection = conexionBD("usuarios_erp");
        String sql = "INSERT INTO usuarios VALUES (?,?,?)";
        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,tfRegistroUsuario.getText());
            statement.setString(2,tfRegistroEmail.getText());
            statement.setString(3,tfRegistroClave.getText());
            statement.setString(3,tfRegistroConfirmaClave.getText());
            statement.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText("Mensaje de información");
            alert.setContentText("USUARIO INSERTADO");

            connection.close();

            panelRegistro.setVisible(false);
            panelLogin.setVisible(true);

        } catch(Exception e) {
            e.printStackTrace();
        }
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

    /**
     * Inserta nueva base de datos tras pulsar 'CREAR'
     */
    @FXML
    void crearBD(ActionEvent event) {
        // Ventana de diálogo para introducir el nombre
        TextInputDialog nombreBD = new TextInputDialog();
        nombreBD.setTitle("INTRODUZCA NOMBRE");
        nombreBD.setHeaderText("Introduzca el nombre de la nueva base de datos");
        Optional<String> resultado = nombreBD.showAndWait();
        // Si se recibe resultado, crea la bbdd y el panel
        if(resultado.isPresent()){
            String nombre = resultado.get();
            nuevaBD(nombre);
            nuevaFila(nombre);
        }
    }

    /**
     * Crea la base de datos con el nombre proporcionado
     */
    private void nuevaBD(String nombre) {
        try {
            String dbName = formatoNombre(nombre);
            // Conexión con MySQL
            Connection connection = conexionMySQL();
            Statement statement = connection.createStatement();

            // Creación de la base de datos
            statement.executeUpdate("CREATE DATABASE " + dbName);
            statement.close();
            connection.close();
            crearTablas(dbName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String formatoNombre(String nombre) {
        return "erp_" + usuario.getNombre() + "_" + nombre; // Nombre de la BD en la memoria
    }

    /**
     * Añade las tablas necesarias para el ERP a la base de datos dada
     */
    private void crearTablas(String nombreBD) {
        try {
            // Conexión con la base de datos
            Connection connection = conexionBD(nombreBD);
            Statement statement = connection.createStatement();

            // Creación de las tablas
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS compras (id INT AUTO_INCREMENT PRIMARY KEY, product_id INT, quantity INT, price DOUBLE);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS facturas (id INT AUTO_INCREMENT PRIMARY KEY, date DATE, total DOUBLE);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS productos (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), stock INT, price DOUBLE);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ventas (id INT AUTO_INCREMENT PRIMARY KEY, product_id INT, quantity INT, price DOUBLE);");

            // Cerramos las conexiones
            statement.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Añade una nueva fila al GridPane que muestra las bases de datos del usuario
     */
    void nuevaFila(String nombre) {
        Label lNombre = new Label(nombre);
        lNombre.setId("labelConectar");
        Button btnConectar = new Button("CONECTAR >");
        btnConectar.setId("boton");
        Button btnEliminar = new Button("ELIMINAR");
        btnEliminar.setId("botonEliminar");

        btnConectar.setOnAction(accesoBD(nombre));
        btnEliminar.setOnAction(accesoBD(nombre));

        HBox hbox = new HBox(lNombre, btnConectar, btnEliminar);
        hbox.setSpacing(300);

        StackPane sp = new StackPane(hbox);
        sp.setId("panelConectar");

        gpBasesDeDatos.addRow(contadorFilas + 1, sp);
        GridPane.setHalignment(sp, HPos.CENTER);
        GridPane.setFillWidth(sp, true);
        contadorFilas++;
    }


    private EventHandler<ActionEvent> accesoBD(String nombre) {
        return event -> {
            try {
                bdSeleccionada = conexionBD(formatoNombre(nombre));
                Dialog<String> ventana = new Dialog<>();
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                ventana.setContentText("CONECTADO");
                ventana.getDialogPane().getButtonTypes().add(type);
                ventana.showAndWait();
            } catch (Exception e){
                e.printStackTrace();
            }
        };
    }

    /* INICIALIZACIÓN */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuario = new Usuario("Fran", "1234", "a@a.a");
        labelBienvenido.setText(labelBienvenido.getText()+usuario.getNombre());
    }

}
