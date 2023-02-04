package safa.sge_erp;

import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import static safa.sge_erp.ConexionBD.conexionBD;
import static safa.sge_erp.ConexionBD.conexionMySQL;


public class ControladorPrincipal implements Initializable {

    //public static FXMLLoader fxmlLoader;
    @FXML
    private AnchorPane panelUsuario;

    @FXML
    private AnchorPane panelLogin;

    @FXML
    private TextField tfLoginUsuario;

    @FXML
    private TextField tfLoginClave;

    @FXML
    private Button btnAcceder;

    @FXML
    private Button btnRegistro;

    @FXML
    private AnchorPane panelRegistro;

    @FXML
    private TextField tfRegistroUsuario;

    @FXML
    private TextField tfRegistroEmail;

    @FXML
    private TextField tfRegistroClave;

    @FXML
    private TextField tfRegistroConfirmaClave;

    @FXML
    private Button btnAceptarRegistro;

    @FXML
    private Button btnCancelarRegistro;

    @FXML
    private AnchorPane panelBD;

    @FXML
    private Button btnCrear;

    @FXML
    private Label labelBienvenido;

    @FXML
    private GridPane gpBasesDeDatos;

    @FXML
    private AnchorPane panelConectado;

    @FXML
    private AnchorPane panelMenu;

    @FXML
    private Button btnCompras;

    @FXML
    private Button btnVentas;

    @FXML
    private Button btnInventario;

    @FXML
    private Button btnFacturas;

    @FXML
    private AnchorPane panelCompra;

    @FXML
    private AnchorPane panelVentas;

    @FXML
    private AnchorPane panelInventario;

    @FXML
    private AnchorPane panelFacturas;



    // Atributos
    Usuario usuario;
    int contadorFilas = 0;
    Connection bdSeleccionada;
    Gson gson = new Gson();
    int counter = 0;


    // Cambio de paneles

    @FXML
    void entrarCompras(ActionEvent event) {
        panelMenu.setVisible(false);
        panelCompra.setVisible(true);
    }

    @FXML
    void entrarFacturas(ActionEvent event) {
        panelMenu.setVisible(false);
        panelFacturas.setVisible(true);
    }

    @FXML
    void entrarInventario(ActionEvent event) {
        panelMenu.setVisible(false);
        panelInventario.setVisible(true);
    }

    @FXML
    void entrarVentas(ActionEvent event) {
        panelMenu.setVisible(false);
        panelVentas.setVisible(true);
    }

    // Estilos botones & imágenes



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
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                // Creamos el usuario
                usuario = new Usuario(rs.getString("nombre"), rs.getString("clave"),
                        rs.getString("email"));
                cargaBasesDatos(rs);

                // Cerramos la conexión con la base de datos de usuarios
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

    private void cargaBasesDatos(ResultSet rs) throws SQLException {
        // Cargamos su tabla de bases de datos
        String json = rs.getString("basesDatos");
        if (json != null){ // Comprobamos que tenga alguna base de datos creada
            List<String> basesDeDatos = gson.fromJson(json, new TypeToken<List<String>>() {}.getType());
            usuario.setBasesDeDatos(basesDeDatos);
            for (String bd : basesDeDatos){
                nuevaFila(bd);
            }
        }
    }


    @FXML
    void aceptaRegistro(ActionEvent event) throws SQLException, ClassNotFoundException {
        Connection connection = conexionBD("usuarios_erp");
        String sql = "INSERT INTO usuarios (nombre, clave, email) VALUES (?,?,?)";
        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,tfRegistroUsuario.getText());
            statement.setString(2,tfRegistroClave.getText());
            statement.setString(3,tfRegistroEmail.getText());
            System.out.println(statement);
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
    void crearBD(ActionEvent event) throws SQLException, ClassNotFoundException {
        // Ventana de diálogo para introducir el nombre
        TextInputDialog nombreBD = new TextInputDialog();
        nombreBD.setTitle("INTRODUZCA NOMBRE");
        nombreBD.setHeaderText("Introduzca el nombre de la nueva base de datos");
        Optional<String> resultado = nombreBD.showAndWait();
        // Si se recibe resultado, crea la bbdd y el panel
        if(resultado.isPresent()){
            String nombre = resultado.get().replace(" ", "_");
            nuevaBD(nombre);
            nuevaFila(nombre);
            actualizaBD(nombre);
        }
    }

    /**
     * Actualiza la base de datos de usuario con la nueva base de datos
     */
    private void actualizaBD(String nombre) throws SQLException, ClassNotFoundException {
        // Añadimos la nueva bd a la lista y la pasamos a json
        List<String> listaActualizada = usuario.getBasesDeDatos();
        listaActualizada.add(nombre);
        usuario.setBasesDeDatos(listaActualizada);

        String json = gson.toJson(listaActualizada);

        // Nos conectamos a la bd de usuarios_erp para actualizarla
        Connection connection = conexionBD("usuarios_erp");
        String query = "UPDATE usuarios SET basesDatos = ? WHERE nombre = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, json);
        statement.setString(2, usuario.nombre);
        System.out.println(statement);
        statement.executeUpdate();

        // Cerramos la conexión
        statement.close();
        connection.close();
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
        lNombre.setMinWidth(750);
        lNombre.setMaxWidth(750);
        Button btnConectar = new Button("CONECTAR >");
        btnConectar.setId("boton");
        Button btnEliminar = new Button("ELIMINAR");
        btnEliminar.setId("botonEliminar");

        btnConectar.setOnAction(accesoBD(nombre));
        btnEliminar.setOnAction(accesoBD(nombre));

        HBox hbox = new HBox(lNombre, btnConectar, btnEliminar);
        hbox.setSpacing(50);

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
