package safa.sge_erp;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private Button btnCrearCompra;

    @FXML
    private Button btnCrearVenta;

    @FXML
    private Button btnComprasVolver;

    @FXML
    private Button btnVentasVolver;

    @FXML
    private TableView<Compra> tvCompras;

    @FXML
    private TableColumn<Compra, Integer> colRef;

    @FXML
    private TableColumn<Compra, String> colNombre;

    @FXML
    private TableColumn<Compra, Float> colPrecioUnitario;

    @FXML
    private TableColumn<Compra, Integer> colCantidad;

    @FXML
    private TableColumn<Compra, Float> colPrecioTotal;

    @FXML
    private TableColumn<Compra, String> colProveedor;

    @FXML
    private TableColumn<Compra, String> colDetalle;

    @FXML
    private TextField tfBuscarCompra;

    @FXML
    private Button btnCompraBuscar;

    @FXML
    private AnchorPane panelFormularioCompra;

    @FXML
    private TextField tfFormCompraReferencia;

    @FXML
    private TextField tfFormCompraNombre;

    @FXML
    private TextField tfFormCompraCantidad;

    @FXML
    private TextField tfFormCompraProveedor;

    @FXML
    private TextField tfFormCompraPrecioUnit;

    @FXML
    private TextField tfFormCompraDetalle;

    @FXML
    private Button btnFormCompraCrear;

    @FXML
    private Button btnVolverCompra;

    @FXML
    private Button btnComprasBorrar;

    @FXML
    private Button btnComprasModificar;





    // Atributos
    Usuario usuario;
    // Compra compra;
    int contadorFilas = 0;
    Connection bdSeleccionada;
    Gson gson = new Gson();
    int counter = 0;
    Boolean editaCompra;



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
    @FXML
    void volverMenu(ActionEvent event) {
        panelCompra.setVisible(false);
        panelVentas.setVisible(false);
        panelFacturas.setVisible(false);
        panelInventario.setVisible(false);
        panelMenu.setVisible(true);
    }

    @FXML
    void volverCompras() {
        panelFormularioCompra.setVisible(false);
        panelCompra.setVisible(true);
        panelVentas.setVisible(false);
        panelFacturas.setVisible(false);
        panelInventario.setVisible(false);

        tfFormCompraReferencia.setText("");
        tfFormCompraReferencia.setId("tfNormal");
        tfFormCompraNombre.setText("");
        tfFormCompraNombre.setId("tfNormal");
        tfFormCompraPrecioUnit.setText("");
        tfFormCompraPrecioUnit.setId("tfNormal");
        tfFormCompraCantidad.setText("");
        tfFormCompraCantidad.setId("tfNormal");
        tfFormCompraProveedor.setText("");
        tfFormCompraProveedor.setId("tfNormal");
        tfFormCompraDetalle.setText("");
        tfFormCompraDetalle.setId("tfNormal");

    }


    // Métodos

    /* MÉTODOS COMUNES */
    private static void ventanaDialogo(String titulo, String mensaje) {
        // Ventana de error
        Dialog<String> ventana = new Dialog<>();
        ventana.setTitle(titulo);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ventana.setContentText(mensaje);
        ventana.getDialogPane().getButtonTypes().add(type);
        ventana.showAndWait();
    }

    public static Alert ventanaConfirmacion(String titulo, String cabecera, String contenido) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);

        ButtonType buttonTypeYes = new ButtonType("Si");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        return alert;
    }

    String leerCampo(String nombrecampo, String texto, String criterioValidacion) {
        return (texto == null || !texto.matches(criterioValidacion)) ? null : texto;
    }


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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS compras ('referencia' INT PRIMARY KEY, 'nombre' VARCHAR(50), 'precio' FLOAT, 'cantidad' INT, 'total' FLOAT, 'proveedor' VARCHAR(50), 'detalle' VARCHAR(100));");
            //statement.executeUpdate("CREATE TABLE IF NOT EXISTS facturas (id INT AUTO_INCREMENT PRIMARY KEY, total DOUBLE);"); // ME DAN ERROR LA CREACION DE TABLAS: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near ''referencia' INT PRIMARY KEY, 'nombre' VARCHAR, 'precio' FLOAT, 'cantidad' IN...' at line 1
           // statement.executeUpdate("CREATE TABLE IF NOT EXISTS productos (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), stock INT, price DOUBLE);");
           // statement.executeUpdate("CREATE TABLE IF NOT EXISTS ventas (id INT AUTO_INCREMENT PRIMARY KEY, product_id INT, quantity INT, price DOUBLE);");

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

    /* PANEL COMPRAS */

    Compra leerValoresCompra(){
        String nombre = leerCampo("Nombre", tfFormCompraNombre.getText(), ".{1,50}");
        Float precio = Float.valueOf(leerCampo("Nombre", tfFormCompraPrecioUnit.getText(), "^[1-9]\\d*(\\.\\d+)?$"));
        Integer cantidad = Integer.valueOf(leerCampo("Nombre", tfFormCompraCantidad.getText(), "^[1-9]\\d*(\\.\\d+)?$"));
        Float total = precio * cantidad;
        String proveedor = leerCampo("Nombre", tfFormCompraProveedor.getText(), ".{1,50}");
        String detalle = leerCampo("Nombre", tfFormCompraDetalle.getText(), ".{1,50}");
        return new Compra(nombre, precio, cantidad, total, proveedor, detalle);
    }


    @FXML
    void aceptarCompra(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (editaCompra) {
            actualizarCompra();
        } else {
            insertarCompra();
        }
    }

    // Botón borrar
    @FXML
    void borrarCompra(ActionEvent event) throws SQLException {
        try {
            String nombre = tvCompras.getSelectionModel().getSelectedItem().getNombre();
            Alert confirmar = ventanaConfirmacion("ELIMINAR PEDIDO", "Eliminar pedido", "¿Está seguro de que desea eliminar este pedido?");
            Optional<ButtonType> resultado = confirmar.showAndWait();
            if (resultado.get() == confirmar.getButtonTypes().get(0)) {
                eliminarCompra(nombre);
                cargarTablaCompra();
            }
        } catch (NullPointerException | ClassNotFoundException e) {
            ventanaDialogo("ERROR", "No hay ningún pedido seleccionado");
        }
    }

    // Botón modificar
    @FXML
    void modificarCompra(ActionEvent event) throws SQLException {
        try {
            cargarFormUsuarios(tvCompras.getSelectionModel().getSelectedItem().getReferencia());
            panelFormularioCompra.setVisible(true);
            editaCompra = true;
        } catch (NullPointerException | ClassNotFoundException npe) {
            ventanaDialogo("ERROR", "No hay ningún usuario seleccionado");
        }
    }


    private void cargarFormUsuarios(Integer referencia) throws SQLException, ClassNotFoundException {
        Compra compra = null;
        Statement statement = conexionBD("usuarios_erp").createStatement();
        String query = "SELECT * FROM compras WHERE referencia = '" + referencia + "'";
        ResultSet datos = statement.executeQuery(query);
        while (datos.next()) {
            compra = new Compra(datos.getInt("referencia"), datos.getString("nombre"), datos.getFloat("precio"), datos.getInt("cantidad"), datos.getFloat("total"), datos.getString("proveedor"), datos.getString("detalle"));
        }
        tfFormCompraReferencia.setEditable(false);
        tfFormCompraReferencia.setText(String.valueOf(compra.getReferencia()));
        tfFormCompraNombre.setText(compra.getNombre());
        tfFormCompraPrecioUnit.setText(String.valueOf(compra.getPrecio()));
        tfFormCompraCantidad.setText(String.valueOf(compra.getCantidad()));
        tfFormCompraProveedor.setText(compra.getProveedor());
        tfFormCompraDetalle.setText(compra.getDetalle());
    }

    // Botón crear de panel compra
    @FXML
    void crearPedidoCompra(ActionEvent event) {
        panelCompra.setVisible(false);
        panelFormularioCompra.setVisible(true);

        editaCompra = false;
        tfFormCompraReferencia.setEditable(true);
    }


    // Cargar en la tabla los pedidos
    @FXML
    void cargarTablaCompra() throws SQLException, ClassNotFoundException {
        ObservableList<Compra> listaCompras = FXCollections.observableArrayList();

        // Conexión con la base de datos
        //Connection connection = conexionBD("usuarios_erp");
        Statement statement = conexionBD("usuarios_erp").createStatement();

        String query = "SELECT * FROM compras";
        String filtro = tfBuscarCompra.getText();
        if (!filtro.equals("")) {
            query += " WHERE " + "referencia LIKE '%" + filtro + "%' OR " + "nombre LIKE '%" + filtro + "%' OR " + "proveedor LIKE '%" + filtro + "%' OR " + "detalle LIKE '%" + filtro + "%';";
        }

        ResultSet datos = statement.executeQuery(query);
        while (datos.next()) {
            listaCompras.add(new Compra(datos.getInt("referencia"), datos.getString("nombre"), datos.getFloat("precio"), datos.getInt("cantidad"), datos.getFloat("total"), datos.getString("proveedor"), datos.getString("detalle")));

        }
        tvCompras.setItems(listaCompras);

        colRef.setCellValueFactory(new PropertyValueFactory<>("referencia"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecioTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        colDetalle.setCellValueFactory(new PropertyValueFactory<>("detalle"));
    }


    Compra leerValoresCompra(){
        Integer referencia = Integer.valueOf(leerCampo("referencia",tfFormCompraReferencia.getText(),"^[1-9]\\d*(\\.\\d+)?$"));
        String nombre = leerCampo("nombre", tfFormCompraNombre.getText(), ".{1,50}");
        Float precio = Float.valueOf(leerCampo("precio", tfFormCompraPrecioUnit.getText(), "^[1-9]\\d*(\\.\\d+)?$"));
        Integer cantidad = Integer.valueOf(leerCampo("cantidad", tfFormCompraCantidad.getText(), "^[1-9]\\d*(\\.\\d+)?$"));
        Float total = precio * cantidad;
        String proveedor = leerCampo("proveedor", tfFormCompraProveedor.getText(), ".{1,50}");
        String detalle = leerCampo("detalle", tfFormCompraDetalle.getText(), ".{1,50}");
        return new Compra(referencia, nombre, precio, cantidad, total, proveedor, detalle);
    }

    // métodos comprueba error

    private boolean compruebaReferencia(String referencia, StringBuilder mensajeError) {
        if (referencia == null) {
            mensajeError.append("Referencia (Escriba la referencia correcta)\n");
            tfFormCompraReferencia.setId("tfError");
            return true;
        } else {
            tfFormCompraReferencia.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaNombre(String nombre, StringBuilder mensajeError) {
        if (nombre == null) {
            mensajeError.append("Nombre (No puede estar vacío. Máximo 50 caracteres)\n");
            tfFormCompraNombre.setId("tfError");
            return true;
        } else {
            tfFormCompraNombre.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaPrecio(Float precio, StringBuilder mensajeError) {
        if (precio == null) {
            mensajeError.append("Precio (No puede estar vacío. Ingrese una cantidad)\n");
            tfFormCompraPrecioUnit.setId("tfError");
            return true;
        } else {
            tfFormCompraPrecioUnit.setId("tfNormal");
        }
        return false;
    }
    private boolean compruebaCantidad(Integer cantidad, StringBuilder mensajeError) {
        if (cantidad == null) {
            mensajeError.append("Precio (No puede estar vacío. Ingrese una cantidad)\n");
            tfFormCompraCantidad.setId("tfError");
            return true;
        } else {
            tfFormCompraCantidad.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaProveedor(String proveedor, StringBuilder mensajeError) {
        if (proveedor == null) {
            mensajeError.append("Proveedor (No puede estar vacío.)\n");
            tfFormCompraProveedor.setId("tfError");
            return true;
        } else {
            tfFormCompraProveedor.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaDetalle(String detalle, StringBuilder mensajeError) {
        if (detalle == null) {
            mensajeError.append("Detalle (No puede estar vacío.)\n");
            tfFormCompraDetalle.setId("tfError");
            return true;
        } else {
            tfFormCompraDetalle.setId("tfNormal");
        }
        return false;
    }


    boolean mensajeErrorCompra(Compra compra) {
        boolean hayError = false;
        StringBuilder mensajeError = new StringBuilder();

        hayError = compruebaReferencia(compra.getNombre(), mensajeError) ? true : hayError;
        hayError = compruebaNombre(compra.getNombre(), mensajeError) ? true : hayError;
        hayError = compruebaPrecio(compra.getPrecio(), mensajeError) ? true : hayError;
        hayError = compruebaCantidad(compra.getCantidad(), mensajeError) ? true : hayError;
        hayError = compruebaProveedor(compra.getProveedor(), mensajeError) ? true : hayError;
        hayError = compruebaDetalle(compra.getDetalle(), mensajeError) ? true : hayError;


        if (hayError) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("CAMPOS ERRÓNEOS");
            error.setContentText("Hay error en los siguientes campos:\n" + mensajeError.toString());
            error.showAndWait();
        }
        return hayError;


    }

    // INSERT
    private void insertarCompra() throws SQLException, ClassNotFoundException {
        Compra compra = leerValoresCompra();
        if (!mensajeErrorCompra(compra)) {
            consultaInsertarPedidoCompra(compra);
            cargarTablaCompra();
            volverCompras();
        }
    }


    private void consultaInsertarPedidoCompra(Compra compra) throws SQLException, ClassNotFoundException {
        // Conexión con la base de datos
        Connection connection = conexionBD("usuarios_erp");


        String sql = "INSERT INTO compras (referencia, nombre, precio, cantidad, total, proveedor, detalle) " + "VALUES (?, ?, ?, ?, ?, ?, ?)"; // Consulta para insertar pedido producto en la base de datos
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            int i = 1;
            statement.setInt(1, compra.getReferencia());
            statement.setString(2, compra.getNombre());
            statement.setFloat(3, compra.getPrecio());
            statement.setInt(4, compra.getCantidad());
            statement.setFloat(5, compra.getTotal());
            statement.setString(6, compra.getProveedor());
            statement.setString(7, compra.getDetalle());


            statement.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("INSERTAR PEDIDO", "Pedido insertado con éxito");
        }
    }

    // DELETE
    void eliminarCompra(String referencia) throws SQLException, ClassNotFoundException {
        // Conexión con la base de datos
        Connection connection = conexionBD("usuarios_erp");

        String sql = "DELETE FROM compras WHERE referencia = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, referencia);
            statement.executeUpdate();
        }
    }

    // UPDATE
    private void actualizarCompra() throws SQLException, ClassNotFoundException {
        Compra compra = leerValoresCompra();
        if (!mensajeErrorCompra(compra)) {
            consultaActualizarCompra(compra);
            cargarTablaCompra();
            volverCompras();
        }
    }

    private void consultaActualizarCompra(Compra compra) throws SQLException, ClassNotFoundException {
        // Conexión con la base de datos
        Connection conexionBD = conexionBD("usuarios_erp");
        String sql = "UPDATE compras SET nombre=?, precio=?, cantidad=?, total=?, proveedor=?, detalle=? WHERE referencia=?";
        try (PreparedStatement statement = conexionBD.prepareStatement(sql)) {
            int i = 1;
            statement.setString(1, compra.getNombre());
            statement.setFloat(2, compra.getPrecio());
            statement.setInt(3, compra.getCantidad());
            statement.setFloat(4, compra.getTotal());
            statement.setString(5, compra.getProveedor());
            statement.setString(6, compra.getDetalle());

            statement.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("ACTUALIZAR PEDIDO", "Pedido actualizado con éxito");
        }
    }

    // private void cargarFormCompra


    /* PANEL VENTAS */

    @FXML
    void crearPedidoVenta(ActionEvent event) {

    }



    /* PANEL INVENTARIO */

    /* PANEL FACTURACIÓN */

    /* INICIALIZACIÓN */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuario = new Usuario("Fran", "1234", "a@a.a");
        labelBienvenido.setText(labelBienvenido.getText()+usuario.getNombre());
    }

}
