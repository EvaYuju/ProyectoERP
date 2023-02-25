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

    @FXML
    private Button btnAcceder;

    @FXML
    private Button btnAceptarProducto;

    @FXML
    private Button btnAceptarRegistro;

    @FXML
    private Button btnCancelarRegistro;

    @FXML
    private Button btnCompraBuscar;

    @FXML
    private Button btnCompras;

    @FXML
    private Button btnCompraFactura;

    @FXML
    private Button btnVentasFactura;

    @FXML
    private Button btnComprasBorrar;

    @FXML
    private Button btnComprasModificar;

    @FXML
    private Button btnComprasVolver;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnCrearCompra;

    @FXML
    private Button btnCrearProducto;

    @FXML
    private Button btnCrearVenta;

    @FXML
    private Button btnFacturaBorrar;

    @FXML
    private Button btnFacturaBuscar;

    @FXML
    private Button btnFacturaPDF;

    @FXML
    private Button btnFacturas;

    @FXML
    private Button btnFormCompraCrear;

    @FXML
    private Button btnFormVentaCrear;

    @FXML
    private Button btnInventario;

    @FXML
    private Button btnProductoModificar;

    @FXML
    private Button btnProductoVolver;

    @FXML
    private Button btnProductosBuscar;

    @FXML
    private Button btnRegistro;

    @FXML
    private Button btnVentaBuscar;

    @FXML
    private Button btnVentas;

    @FXML
    private Button btnVentasBorrar;

    @FXML
    private Button btnVentasBorrar1;

    @FXML
    private Button btnVentasModificar;

    @FXML
    private Button btnVentasVolver;

    @FXML
    private Button btnVentasVolver11;

    @FXML
    private Button btnVolverCompra;

    @FXML
    private Button btnVolverProducto;

    @FXML
    private Button btnVolverVenta;

    @FXML
    private ComboBox<Producto> cbFormCompraProductos;

    @FXML
    private TableColumn<Compra, Float> colCompraTotal;

    @FXML
    private TableColumn<Venta, Integer> colVentaCantidad;

    @FXML
    private TableColumn<Compra, Integer> colCompraIDProducto;

    @FXML
    private TableColumn<Venta, String> colVentaCliente;

    @FXML
    private TableColumn<Compra, String> colNombre;

    @FXML
    private TableColumn<Venta, String> colVentaDetalle;

    @FXML
    private TableColumn<Compra, String> colCompraProveedor;

    @FXML
    private TableColumn<Venta, Integer> colVentaIDProducto;

    @FXML
    private TableColumn<Compra, Integer> colCompraCantidad;

    @FXML
    private TableColumn<Producto, Integer> colProductoCantidad;

    @FXML
    private TableColumn<Producto, String> colProductoNombre;

    @FXML
    private TableColumn<Producto, Float> colProductoPrecioCompra;

    @FXML
    private TableColumn<Producto, Float> colProductoPrecioVenta;

    @FXML
    private TableColumn<Compra, String> colCompraDetalle;

    @FXML
    private TableColumn<Compra, Integer> colCompraID;

    @FXML
    private TableColumn<Venta, Integer> colVentaID;

    @FXML
    private TableColumn<Venta, Float> colVentaTotal;

    @FXML
    private TableColumn<Factura, Integer> colFacturaID;

    @FXML
    private TableColumn<Factura, Double> colFacturaTotal;

    @FXML
    private TableColumn<Factura, Integer> colFacturaIDCompra;

    @FXML
    private TableColumn<Factura, Integer> colFacturaIDVenta;

    @FXML
    private GridPane gpBasesDeDatos;

    @FXML
    private Label labelBienvenido;

    @FXML
    private MenuItem miCerrarSesion;

    @FXML
    private MenuItem miEditaPerfil;

    @FXML
    private AnchorPane panelBD;

    @FXML
    private AnchorPane panelCompra;

    @FXML
    private AnchorPane panelConectado;

    @FXML
    private AnchorPane panelFacturas;

    @FXML
    private AnchorPane panelFormularioCompra;

    @FXML
    private AnchorPane panelFormularioProducto;

    @FXML
    private AnchorPane panelFormularioVenta;

    @FXML
    private AnchorPane panelLogin;

    @FXML
    private AnchorPane panelMenu;

    @FXML
    private AnchorPane panelProductos;

    @FXML
    private AnchorPane panelRegistro;

    @FXML
    private AnchorPane panelUsuario;

    @FXML
    private AnchorPane panelVentas;

    @FXML
    private TextField tfBuscarCompra;

    @FXML
    private TextField tfBuscarFactura;

    @FXML
    private TextField tfBuscarProducto;

    @FXML
    private TextField tfBuscarVenta;

    @FXML
    private TextField tfFormCompraCantidad;

    @FXML
    private TextField tfFormCompraDetalle;

    @FXML
    private TextField tfFormCompraNombre;

    @FXML
    private TextField tfFormCompraPrecioUnit;

    @FXML
    private TextField tfFormCompraProveedor;

    @FXML
    private TextField tfFormCompraReferencia;

    @FXML
    private TextField tfFormProductoCantidad;

    @FXML
    private TextField tfFormProductoNombre;

    @FXML
    private TextField tfFormProductoPrecioCompra;

    @FXML
    private TextField tfFormProductoPrecioVenta;

    @FXML
    private TextField tfFormVentaCantidad;

    @FXML
    private TextField tfFormVentaCliente;

    @FXML
    private TextField tfFormVentaDetalle;

    @FXML
    private TextField tfFormVentaNombre;

    @FXML
    private TextField tfFormVentaPrecioUnit;

    @FXML
    private ComboBox<Producto> cbFormVentaProductos;

    @FXML
    private PasswordField tfLoginClave;

    @FXML
    private TextField tfLoginUsuario;

    @FXML
    private PasswordField tfRegistroClave;

    @FXML
    private PasswordField tfRegistroConfirmaClave;

    @FXML
    private TextField tfRegistroEmail;

    @FXML
    private TextField tfRegistroUsuario;

    @FXML
    private TableView<Compra> tvCompras;

    @FXML
    private TableView<Factura> tvFacturas;

    @FXML
    private TableView<Producto> tvProductos;

    @FXML
    private TableView<Venta> tvVentas;

    // Atributos
    Usuario usuario;
    int contadorFilas = 0;
    Connection bdSeleccionada;
    Gson gson = new Gson();
    int counter = 0;
    Boolean editaUsuario;
    Boolean editaCompra;
    Boolean editaVenta;
    Boolean editaProducto;
    Integer idProd;
    Integer idCompra;
    Integer idVenta;



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
        panelProductos.setVisible(true);
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
        panelProductos.setVisible(false);
        panelMenu.setVisible(true);
    }

    @FXML
    void volverCompras() {
        panelFormularioCompra.setVisible(false);
        panelCompra.setVisible(true);
        panelVentas.setVisible(false);
        //panelFacturas.setVisible(false);
        //panelInventario.setVisible(false);

        cbFormCompraProductos.setValue(null);
        cbFormCompraProductos.setId("tfNormal");
        tfFormCompraCantidad.setText("");
        tfFormCompraCantidad.setId("tfNormal");
        tfFormCompraProveedor.setText("");
        tfFormCompraProveedor.setId("tfNormal");
        tfFormCompraDetalle.setText("");
        tfFormCompraDetalle.setId("tfNormal");


    }
    @FXML
    void volverVentas() {
        panelFormularioVenta.setVisible(false);
        panelCompra.setVisible(false);
        panelVentas.setVisible(true);
        //panelFacturas.setVisible(false);
        //panelInventario.setVisible(false);

        cbFormVentaProductos.setValue(null);
        cbFormVentaProductos.setId("tfNormal");
        tfFormVentaCantidad.setText("");
        tfFormVentaCantidad.setId("tfNormal");
        tfFormVentaCliente.setText("");
        tfFormVentaCliente.setId("tfNormal");
        tfFormVentaDetalle.setText("");
        tfFormVentaDetalle.setId("tfNormal");
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

    @FXML
    void editaPerfil() throws SQLException, ClassNotFoundException {
        editaUsuario = true;

        tfRegistroUsuario.setEditable(false);
        panelBD.setVisible(false);
        panelConectado.setVisible(false);
        panelUsuario.setVisible(true);
        panelRegistro.setVisible(true);
        panelLogin.setVisible(false);

        cargaFormUsuario();

    }

    void cargaFormUsuario() throws SQLException, ClassNotFoundException {
        Usuario auxUsuario = null;
        Connection connection = conexionBD("usuarios_erp");
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM usuarios WHERE nombre = '" + usuario.getNombre() + "'";
        ResultSet datos = stmt.executeQuery(query);
        while (datos.next()) {
            auxUsuario = new Usuario(datos.getString("nombre"), datos.getString("clave"),
                    datos.getString("email"));
        }

        tfRegistroUsuario.setText(auxUsuario.getNombre());
        tfRegistroEmail.setText(auxUsuario.getEmail());
        tfRegistroClave.setText(auxUsuario.getClave());
        tfRegistroConfirmaClave.setText(auxUsuario.getClave());
    }

    @FXML
    void cerrarSesion(){
        panelBD.setVisible(false);
        panelConectado.setVisible(false);
        panelLogin.setVisible(true);
        clearGridPane(gpBasesDeDatos);
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
                labelBienvenido.setText("Bienvenid@, " +usuario.getNombre());
                cargaBasesDatos(rs);

                // Cerramos la conexión con la base de datos de usuarios
                connection.close();

                // Cargar usuario
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
        // Comprobamos el usuario
        if (tfRegistroUsuario.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Mensaje de error");
            alert.setContentText("El usuario no puede estar vacío");
            alert.showAndWait();
            return;
        }

        // Comprobamos el email
        String email = leerCampo("Email", tfRegistroEmail.getText(), "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$");
        if (email == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Mensaje de error");
            alert.setContentText("El email no es válido");
            alert.showAndWait();
            return;
        }

        // Comprobar que la clave no esté vacía
        if (tfRegistroClave.getText().equals("")){
            // Mostrar mensaje de error si está vacío
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Mensaje de error");
            alert.setContentText("La clave no puede estar vacía");
            alert.showAndWait();
            return;
        }
        // Comprobar si los campos de clave y confirmación de clave son iguales
        if (!tfRegistroClave.getText().equals(tfRegistroConfirmaClave.getText())) {
            // Mostrar un mensaje de error si no coinciden
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Mensaje de error");
            alert.setContentText("Las claves no coinciden");
            alert.showAndWait();
            return;
        }
        if (editaUsuario){
            Connection connection = conexionBD("usuarios_erp");
            String sql = "UPDATE usuarios SET  clave = ?, email = ? WHERE nombre = '" + usuario.getNombre() + "'";
            try {

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, tfRegistroClave.getText());
                statement.setString(2, tfRegistroEmail.getText());
                System.out.println(statement);
                statement.execute();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Mensaje de información");
                alert.setContentText("USUARIO ACTUALIZADO");

                connection.close();

                ventanaDialogo("USUARIO ACTUALIZAD0", "Usuario actualizado con éxito");


                panelRegistro.setVisible(false);
                panelLogin.setVisible(false);
                panelUsuario.setVisible(false);
                panelBD.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Connection connection = conexionBD("usuarios_erp");
            String sql = "INSERT INTO usuarios (nombre, clave, email) VALUES (?,?,?)";
            try {

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, tfRegistroUsuario.getText());
                statement.setString(2, tfRegistroClave.getText());
                statement.setString(3, tfRegistroEmail.getText());
                System.out.println(statement);
                statement.execute();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Mensaje de información");
                alert.setContentText("USUARIO INSERTADO");

                connection.close();

                ventanaDialogo("NUEVO USUARIO", "Usuario insertado con éxito");

                panelRegistro.setVisible(false);
                panelLogin.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    @FXML
    void nuevoRegistro(ActionEvent event) {
        editaUsuario = false;
        tfRegistroUsuario.setEditable(true);
        panelLogin.setVisible(false);
        panelRegistro.setVisible(true);
    }

    @FXML
    void cancelaRegistro(ActionEvent event) {
        tfRegistroEmail.setText("");
        tfRegistroClave.setText("");
        tfRegistroUsuario.setText("");
        tfRegistroConfirmaClave.setText("");
        if (editaUsuario){
            panelUsuario.setVisible(false);
        } else {
            panelRegistro.setVisible(false);
            panelLogin.setVisible(true);
        }

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
            actualizaBD(nombre, false);
        }
    }

    /**
     * Actualiza la base de datos de usuario con la nueva base de datos
     */
    private void actualizaBD(String nombre, boolean borrar) throws SQLException, ClassNotFoundException {

        // Añadimos la nueva bd a la lista y la pasamos a json
        List<String> listaActualizada = usuario.getBasesDeDatos();
        if (!borrar){
            listaActualizada.add(nombre);
        } else {
            listaActualizada.remove(nombre);
        }
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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS productos (idProducto INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(50), cantidad INT, precioCompra FLOAT, precioVenta FLOAT);");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS compras (idCompra INT AUTO_INCREMENT PRIMARY KEY, cantidad INT, total FLOAT, proveedor VARCHAR(50), detalle VARCHAR(100), idProducto INT, "
                    + "FOREIGN KEY (idProducto) REFERENCES productos(idProducto) ON DELETE SET NULL);");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ventas (idVenta INT PRIMARY KEY, cantidad INT, total FLOAT, cliente VARCHAR(50), detalle VARCHAR(100), idProducto INT, "
                    + "FOREIGN KEY (idProducto) REFERENCES productos(idProducto) ON DELETE SET NULL);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS facturas (idFactura INT AUTO_INCREMENT PRIMARY KEY, total DOUBLE, idCompra INT, idVenta INT, "
                    + "FOREIGN KEY (idCompra) REFERENCES compras(idCompra) ON DELETE SET NULL, "
                    + "FOREIGN KEY (idVenta) REFERENCES ventas(idProducto) ON DELETE SET NULL);");
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
        btnEliminar.setOnAction(borrarBD(nombre));

        HBox hbox = new HBox(lNombre, btnConectar, btnEliminar);
        hbox.setSpacing(50);

        StackPane sp = new StackPane(hbox);
        sp.setId("panelConectar");

        gpBasesDeDatos.addRow(contadorFilas + 1, sp);
        GridPane.setHalignment(sp, HPos.CENTER);
        GridPane.setFillWidth(sp, true);
        contadorFilas++;
    }

    /*
    Configura un botón para dar acceso a la base de datos seleccionada
     */
    private EventHandler<ActionEvent> accesoBD(String nombre) {
        return event -> {
            try {
                bdSeleccionada = conexionBD(formatoNombre(nombre));
                Dialog<String> ventana = new Dialog<>();
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                panelBD.setVisible(false);
                panelConectado.setVisible(true);
                panelMenu.setVisible(true);

                cargarTablaProducto();
                cargarTablaCompra();
                cargarTablaVenta();

            } catch (Exception e){
                e.printStackTrace();
            }
        };
    }

    /*
    Configura un botón para borrar la base de datos seleccionada
     */
    private EventHandler<ActionEvent> borrarBD(String nombre) {
        return event -> {
            try {
                Connection con = conexionMySQL();
                Statement stmt = con.createStatement();

                String sql = "DROP DATABASE " + formatoNombre(nombre);
                stmt.executeUpdate(sql);

                ventanaDialogo("ELIMINAR", "Se ha borrado la base de datos: " + nombre);

                actualizaBD(nombre, true);
                contadorFilas = 0;
                clearGridPane(gpBasesDeDatos);
                for (String bd : usuario.getBasesDeDatos()){
                    nuevaFila(bd);
                }


            } catch (Exception e){
                e.printStackTrace();
            }
        };
    }
    public static void clearGridPane(GridPane gridPane) {
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                int finalI = i;
                int finalJ = j;
                gridPane.getChildren().removeIf(node ->
                        GridPane.getRowIndex(node) == finalI && GridPane.getColumnIndex(node) == finalJ);
            }
        }
    }


    /* PANEL COMPRAS */

    @FXML
    void facturaCompra() throws SQLException{
        try{
            Compra compra = tvCompras.getSelectionModel().getSelectedItem();
            Double total = compra.getTotal()*1.21;

            String sql = "INSERT INTO facturas (total, idCompra) VALUES (?,?)";
            try (PreparedStatement stmt = bdSeleccionada.prepareStatement(sql))
            {
                stmt.setInt(1, compra.getIdCompra());
                stmt.setDouble(2, total);

                stmt.executeUpdate();// Ejecutar la consulta
                ventanaDialogo("FACTURA DE COMPRA", "Factura creada con éxito");
            }

        } catch (NullPointerException e) {
            ventanaDialogo("ERROR", "No hay ningún pedido seleccionado");
        }
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
            Integer idCompra = tvCompras.getSelectionModel().getSelectedItem().getIdCompra();
            Alert confirmar = ventanaConfirmacion("ELIMINAR PEDIDO", "Eliminar pedido", "¿Está seguro de que desea eliminar este pedido?");
            Optional<ButtonType> resultado = confirmar.showAndWait();
            if (resultado.get() == confirmar.getButtonTypes().get(0)) {
                eliminarCompra(idCompra);
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

            editaCompra = true;
            idCompra = tvCompras.getSelectionModel().getSelectedItem().getIdCompra();
            cargarFormCompras(idCompra);
            cambiarVistaFormCompra();
        } catch (NullPointerException | ClassNotFoundException npe) {
            ventanaDialogo("ERROR", "No hay ninguna operación seleccionada");
        }
    }

    private void cambiarVistaFormCompra() {
        panelFormularioCompra.setVisible(true);
        panelCompra.setVisible(false);
    }



    private void cargarFormCompras(Integer idCompra) throws SQLException, ClassNotFoundException {

        Compra compra = null;
        Producto producto = null;
        Statement statement = bdSeleccionada.createStatement();
        String query = "SELECT * FROM compras WHERE idCompra = '" + idCompra + "'";
        ResultSet datos = statement.executeQuery(query);
        while (datos.next()) {
            compra = new Compra(datos.getInt("idCompra"), datos.getInt("cantidad"), datos.getFloat("total"),
                    datos.getString("proveedor"), datos.getString("detalle"), datos.getInt("idProducto"));
        }

        query = "SELECT * FROM productos WHERE idProducto = '" + compra.getIdProducto() + "'";
        datos = statement.executeQuery(query);

        while(datos.next()){
            producto = new Producto(datos.getInt("idProducto"), datos.getString("nombre"),
                    datos.getInt("cantidad"), datos.getFloat("precioCompra"), datos.getFloat("precioVenta"));
        }

        cbFormCompraProductos.setValue(producto);
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
    }


    // Cargar en la tabla los pedidos
    @FXML
    void cargarTablaCompra() throws SQLException, ClassNotFoundException {
        ObservableList<Compra> listaCompras = FXCollections.observableArrayList();

        // Conexión con la base de datos
        Statement statement = bdSeleccionada.createStatement();

        String query = "SELECT * FROM compras";
        String filtro = tfBuscarCompra.getText();
        if (!filtro.equals("")) {
            query += " WHERE " + "idCompra LIKE '%" + filtro
                    + "%' OR " + "cantidad LIKE '%" + filtro
                    + "%' OR " + "proveedor LIKE '%" + filtro
                    + "%' OR " + "detalle LIKE '%" + filtro + "%';";
        }

        ResultSet datos = statement.executeQuery(query);
        while (datos.next()) {
            listaCompras.add(new Compra(datos.getInt("idCompra"), datos.getInt("cantidad"), datos.getFloat("total"),
                    datos.getString("proveedor"), datos.getString("detalle"), datos.getInt("idProducto")));

        }
        tvCompras.setItems(listaCompras);

        colCompraID.setCellValueFactory(new PropertyValueFactory<>("idCompra"));
        colCompraCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colCompraTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colCompraProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        colCompraDetalle.setCellValueFactory(new PropertyValueFactory<>("detalle"));
        colCompraIDProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
    }


    Compra leerValoresCompra(){
        String auxProducto = leerCampo("Producto", String.valueOf(cbFormCompraProductos.getValue().getIdProducto()), ".{1,50}");
        String auxCantidad = leerCampo("Cantidad", tfFormCompraCantidad.getText(), "^[0-9]\\d*(\\.\\d+)?$");
        String proveedor = leerCampo("Precio de compra", tfFormCompraProveedor.getText(), ".{1,50}");
        String detalle = leerCampo("Precio de venta", tfFormCompraDetalle.getText(), ".{1,200}");

        Integer producto = null;
        Integer cantidad = null;

        if (auxProducto != null){
            producto = Integer.valueOf(auxProducto);
        }
        if (auxCantidad != null){
            cantidad = Integer.valueOf(auxCantidad);
        }

        return new Compra(cantidad, proveedor, detalle, producto);
    }

    // métodos comprueba error

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

    private boolean compruebaProducto(Integer idProducto, StringBuilder mensajeError) {
        if (idProducto == null) {
            mensajeError.append("Producto (No puede estar vacío. Seleccione un producto)\n");
            cbFormCompraProductos.setId("tfError");
            return true;
        } else {
            cbFormCompraProductos.setId("tfNormal");
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

    private boolean compruebaCliente(String proveedor, StringBuilder mensajeError) {
        if (proveedor == null) {
            mensajeError.append("Cliente (No puede estar vacío.)\n");
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

        hayError = compruebaCantidad(compra.getCantidad(), mensajeError) ? true : hayError;
        hayError = compruebaProveedor(compra.getProveedor(), mensajeError) ? true : hayError;
        hayError = compruebaDetalle(compra.getDetalle(), mensajeError) ? true : hayError;
        hayError = compruebaProducto(compra.getIdProducto(), mensajeError) ? true : hayError;

        if (hayError) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("CAMPOS ERRÓNEOS");
            error.setContentText("Hay error en los siguientes campos:\n" + mensajeError);
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
        Producto producto = null;
        Statement statement = bdSeleccionada.createStatement();
        String query = "SELECT * FROM productos WHERE idProducto = '" + compra.getIdProducto() + "'";
        ResultSet datos = statement.executeQuery(query);

        while(datos.next()){
            producto = new Producto(datos.getInt("idProducto"), datos.getString("nombre"),
                    datos.getInt("cantidad"), datos.getFloat("precioCompra"), datos.getFloat("precioVenta"));
        }

        compra.setTotal(compra.getCantidad() * producto.getPrecioCompra());

        // Conexión con la base de datos
        String sql = "INSERT INTO compras (cantidad, total, proveedor, detalle, idProducto) " + "VALUES ( ?, ?, ?, ?, ?)"; // Consulta para insertar pedido producto en la base de datos
        try (PreparedStatement stmt = bdSeleccionada.prepareStatement(sql))
        {
            stmt.setInt(1, compra.getCantidad());
            stmt.setFloat(2, compra.getTotal());
            stmt.setString(3, compra.getProveedor());
            stmt.setString(4, compra.getDetalle());
            stmt.setInt(5, compra.getIdProducto());

            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("INSERTAR PEDIDO", "Pedido insertado con éxito");
        }
    }

    // DELETE
    void eliminarCompra(Integer referencia) throws SQLException, ClassNotFoundException {
        // Conexión con la base de datos

        String sql = "DELETE FROM compras WHERE nombre = ?";

        try (PreparedStatement statement = bdSeleccionada.prepareStatement(sql)) {

            statement.setString(1, String.valueOf(referencia));
            statement.executeUpdate();
        }
    }

    // UPDATE
    private void actualizarCompra() throws SQLException, ClassNotFoundException {
        Compra compra = leerValoresCompra();
        compra.setIdCompra(idCompra);
        if (!mensajeErrorCompra(compra)) {
            consultaActualizarCompra(compra);
            cargarTablaCompra();
            volverCompras();
        }
    }

    private void consultaActualizarCompra(Compra compra) throws SQLException, ClassNotFoundException {
        Producto producto = null;
        Statement statement = bdSeleccionada.createStatement();
        String query = "SELECT * FROM productos WHERE idProducto = '" + compra.getIdProducto() + "'";
        ResultSet datos = statement.executeQuery(query);

        while(datos.next()){
            producto = new Producto(datos.getInt("idProducto"), datos.getString("nombre"),
                    datos.getInt("cantidad"), datos.getFloat("precioCompra"), datos.getFloat("precioVenta"));
        }

        compra.setTotal(compra.getCantidad() * producto.getPrecioCompra());

        // Conexión con la base de datos
        String sql = "UPDATE compras SET cantidad=?, total=?, proveedor=?, detalle=?, idProducto=? WHERE idCompra=?";
        try (PreparedStatement stmt = bdSeleccionada.prepareStatement(sql)) {
            stmt.setInt(1, compra.getCantidad());
            stmt.setFloat(2, compra.getTotal());
            stmt.setString(3, compra.getProveedor());
            stmt.setString(4, compra.getDetalle());
            stmt.setInt(5, compra.getIdProducto());
            stmt.setInt(6, compra.getIdCompra());


            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("ACTUALIZAR PEDIDO", "Pedido actualizado con éxito");
        }
    }




    /* PANEL VENTAS */

    @FXML
    void facturaVenta() throws SQLException{
        try{
            Venta venta = tvVentas.getSelectionModel().getSelectedItem();
            Double total = venta.getTotal()*1.21;

            String sql = "INSERT INTO facturas (total, idVenta) VALUES (?,?)";
            try (PreparedStatement stmt = bdSeleccionada.prepareStatement(sql))
            {
                stmt.setInt(1, venta.getIdVenta());
                stmt.setDouble(2, total);

                stmt.executeUpdate();// Ejecutar la consulta
                ventanaDialogo("FACTURA DE VENTA", "Factura creada con éxito");
            }

        } catch (NullPointerException e) {
            ventanaDialogo("ERROR", "No hay ningún pedido seleccionado");
        }
    }

    // En formulario pedido -> clic botón crear pedido
    @FXML
    void aceptarVenta(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (editaVenta) {
            actualizarVenta();
        } else {
            insertarVenta();
        }
    }

    // Botón borrar
    @FXML
    void borrarVenta(ActionEvent event) throws SQLException {
        try {
            Integer idVenta = tvVentas.getSelectionModel().getSelectedItem().getIdVenta();
            Alert confirmar = ventanaConfirmacion("ELIMINAR VENTA", "Eliminar venta", "¿Está seguro de que desea " +
                    "eliminar esta venta?");
            Optional<ButtonType> resultado = confirmar.showAndWait();
            if (resultado.get() == confirmar.getButtonTypes().get(0)) {
                eliminarVenta(idCompra);
                cargarTablaVenta();
            }
        } catch (NullPointerException | ClassNotFoundException e) {
            ventanaDialogo("ERROR", "No hay ningúna venta seleccionado");
        }
    }

    // Botón modificar
    @FXML
    void modificarVenta(ActionEvent event) throws SQLException {
        try {
            editaVenta = true;
            idVenta = tvVentas.getSelectionModel().getSelectedItem().getIdVenta();
            cargarFormVentas(idVenta);
            cambiarVistaFormVenta();

        } catch (NullPointerException | ClassNotFoundException npe) {
            ventanaDialogo("ERROR", "No hay ningúna venta seleccionada");
        }

    }

    private void cambiarVistaFormVenta() {
        panelVentas.setVisible(false);
        panelFormularioVenta.setVisible(true);
    }

    private void cargarFormVentas(Integer idVenta) throws SQLException, ClassNotFoundException {
        Venta venta = null;
        Producto producto = null;
        Statement statement = bdSeleccionada.createStatement();
        String query = "SELECT * FROM ventas WHERE idVenta = '" + idVenta + "'";
        ResultSet datos = statement.executeQuery(query);
        while (datos.next()) {
            venta = new Venta(datos.getInt("idVenta"), datos.getInt("cantidad"), datos.getFloat("total"),
                    datos.getString("cliente"), datos.getString("detalle"), datos.getInt("idProducto"));
        }

        query = "SELECT * FROM productos WHERE idProducto = '" + venta.getIdProducto() + "'";
        datos = statement.executeQuery(query);

        while(datos.next()){
            producto = new Producto(datos.getInt("idProducto"), datos.getString("nombre"),
                    datos.getInt("cantidad"), datos.getFloat("precioCompra"), datos.getFloat("precioVenta"));
        }


        cbFormVentaProductos.setValue(producto);
        tfFormVentaCantidad.setText(String.valueOf(venta.getCantidad()));
        tfFormVentaCliente.setText(venta.getCliente());
        tfFormVentaDetalle.setText(venta.getDetalle());
    }

    // Botón crear de panel compra
    @FXML
    void crearPedidoVenta(ActionEvent event) {
        panelVentas.setVisible(false);
        panelFormularioVenta.setVisible(true);

        editaVenta = false;

    }

    // Cargar en la tabla los pedidos
    @FXML
    void cargarTablaVenta() throws SQLException, ClassNotFoundException {
        ObservableList<Venta> listaVentas = FXCollections.observableArrayList();

        // Conexión con la base de datos

        Statement statement = bdSeleccionada.createStatement();

        String query = "SELECT * FROM ventas";
        String filtro = tfBuscarVenta.getText();
        if (!filtro.equals("")) {
            query += " WHERE " + "idVenta LIKE '%" + filtro
                    + "%' OR " + "cantidad LIKE '%" + filtro
                    + "%' OR " + "cliente LIKE '%" + filtro
                    + "%' OR " + "detalle LIKE '%" + filtro + "%';";
        }

        ResultSet datos = statement.executeQuery(query);
        while (datos.next()) {
            listaVentas.add(new Venta(datos.getInt("idVenta"), datos.getInt("cantidad"), datos.getFloat("total"),
                    datos.getString("cliente"), datos.getString("detalle"), datos.getInt("idProducto")));

        }
        tvVentas.setItems(listaVentas);

        colVentaID.setCellValueFactory(new PropertyValueFactory<>("idVenta"));
        colVentaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colVentaTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colVentaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colVentaDetalle.setCellValueFactory(new PropertyValueFactory<>("detalle"));
        colVentaIDProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));

    }

    Venta leerValoresVenta(){
        String auxProducto = leerCampo("Producto", String.valueOf(cbFormVentaProductos.getValue().getIdProducto()), ".{1,50}");
        String auxCantidad = leerCampo("Cantidad", tfFormVentaCantidad.getText(), "^[0-9]\\d*(\\.\\d+)?$");
        String cliente = leerCampo("Precio de compra", tfFormVentaCliente.getText(), ".{1,50}");
        String detalle = leerCampo("Precio de venta", tfFormVentaDetalle.getText(), ".{1,200}");

        Integer producto = null;
        Integer cantidad = null;

        if (auxProducto != null){
            producto = Integer.valueOf(auxProducto);
        }
        if (auxCantidad != null){
            cantidad = Integer.valueOf(auxCantidad);
        }

        return new Venta(cantidad, cliente, detalle, producto);
    }


    // métodos comprueba error


    private boolean compruebaNombreV(String nombre, StringBuilder mensajeError) {
        if (nombre == null) {
            mensajeError.append("Nombre (No puede estar vacío. Máximo 50 caracteres)\n");
            tfFormVentaNombre.setId("tfError");
            return true;
        } else {
            tfFormVentaNombre.setId("tfError");
        }
        return false;
    }

    private boolean compruebaProveedorV(String cliente, StringBuilder mensajeError) {
        if (cliente == null) {
            mensajeError.append("Cliente (No puede estar vacío.)\n");
            tfFormVentaCliente.setId("tfError");
            return true;
        } else {
            tfFormVentaCliente.setId("tfNormal");
        }
        return false;
    }

    private boolean compruebaPrecioV(Float precio, StringBuilder mensajeError) {
        if (precio == null) {
            mensajeError.append("Precio (No puede estar vacío. Ingrese una cantidad)\n");
            tfFormVentaPrecioUnit.setId("tfError");
            return true;
        } else {
            tfFormVentaPrecioUnit.setId("tfNormal");
        }
        return false;
    }
    private boolean compruebaCantidadV(Integer cantidad, StringBuilder mensajeError) {
        if (cantidad == null) {
            mensajeError.append("Precio (No puede estar vacío. Ingrese una cantidad)\n");
            tfFormVentaCantidad.setId("tfError");
            return true;
        } else {
            tfFormVentaCantidad.setId("tfNormal");
        }
        return false;
    }


    private boolean compruebaDetalleV(String detalle, StringBuilder mensajeError) {
        if (detalle == null) {
            mensajeError.append("Detalle (No puede estar vacío.)\n");
            tfFormVentaDetalle.setId("tfError");
            return true;
        } else {
            tfFormVentaDetalle.setId("tfNormal");
        }
        return false;
    }


    boolean mensajeErrorVenta(Venta venta) {
        boolean hayError = false;
        StringBuilder mensajeError = new StringBuilder();

        hayError = compruebaCantidad(venta.getCantidad(), mensajeError) ? true : hayError;
        hayError = compruebaCliente(venta.getCliente(), mensajeError) ? true : hayError;
        hayError = compruebaDetalle(venta.getDetalle(), mensajeError) ? true : hayError;
        hayError = compruebaProducto(venta.getIdProducto(), mensajeError) ? true : hayError;

        if (hayError) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("CAMPOS ERRÓNEOS");
            error.setContentText("Hay error en los siguientes campos:\n" + mensajeError);
            error.showAndWait();
        }
        return hayError;


    }

    // INSERT
    private void insertarVenta() throws SQLException, ClassNotFoundException {
        Venta venta = leerValoresVenta();
        if (!mensajeErrorVenta(venta)) {
            consultaInsertarPedidoVenta(venta);
            cargarTablaVenta();
            volverVentas();
        }
    }

    private void consultaInsertarPedidoVenta(Venta venta) throws SQLException, ClassNotFoundException {
        Producto producto = null;
        Statement statement = bdSeleccionada.createStatement();
        String query = "SELECT * FROM productos WHERE idProducto = '" + venta.getIdProducto() + "'";
        ResultSet datos = statement.executeQuery(query);

        while(datos.next()){
            producto = new Producto(datos.getInt("idProducto"), datos.getString("nombre"),
                    datos.getInt("cantidad"), datos.getFloat("precioCompra"), datos.getFloat("precioVenta"));
        }

        venta.setTotal(venta.getCantidad() * producto.getPrecioCompra());

        // Conexión con la base de datos
        String sql = "INSERT INTO ventas (cantidad, total, cliente, detalle, idProducto) " + "VALUES ( ?, ?, ?, ?, ?)"; // Consulta para insertar pedido producto en la base de datos
        try (PreparedStatement stmt = bdSeleccionada.prepareStatement(sql))
        {
            stmt.setInt(1, venta.getCantidad());
            stmt.setFloat(2, venta.getTotal());
            stmt.setString(3, venta.getCliente());
            stmt.setString(4, venta.getDetalle());
            stmt.setInt(5, venta.getIdProducto());

            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("INSERTAR VENTA", "Venta insertado con éxito");
        }
    }
    // DELETE
    void eliminarVenta(Integer referencia) throws SQLException, ClassNotFoundException {
        // Conexión con la base de datos

        String sql = "DELETE FROM ventas WHERE nombre = ?";

        try (PreparedStatement statement = bdSeleccionada.prepareStatement(sql)) {

            statement.setString(1, String.valueOf(referencia));
            statement.executeUpdate();
        }
    }
    // UPDATE
    private void actualizarVenta() throws SQLException, ClassNotFoundException {
        Venta venta = leerValoresVenta();
        venta.setIdVenta(idVenta);
        if (!mensajeErrorVenta(venta)) {
            consultaActualizarVenta(venta);
            cargarTablaVenta();
            volverVentas();
        }
    }

    private void consultaActualizarVenta(Venta venta) throws SQLException, ClassNotFoundException {
        Producto producto = null;
        Statement statement = bdSeleccionada.createStatement();
        String query = "SELECT * FROM productos WHERE idProducto = '" + venta.getIdProducto() + "'";
        ResultSet datos = statement.executeQuery(query);

        while(datos.next()){
            producto = new Producto(datos.getInt("idProducto"), datos.getString("nombre"),
                    datos.getInt("cantidad"), datos.getFloat("precioCompra"), datos.getFloat("precioVenta"));
        }

        venta.setTotal(venta.getCantidad() * producto.getPrecioCompra());

        // Conexión con la base de datos
        String sql = "UPDATE compras SET cantidad=?, total=?, cliente=?, detalle=?, idProducto=? WHERE idCompra=?";
        try (PreparedStatement stmt = bdSeleccionada.prepareStatement(sql)) {
            stmt.setInt(1, venta.getCantidad());
            stmt.setFloat(2, venta.getTotal());
            stmt.setString(3, venta.getCliente());
            stmt.setString(4, venta.getDetalle());
            stmt.setInt(5, venta.getIdProducto());
            stmt.setInt(6, venta.getIdVenta());


            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("ACTUALIZAR VENTA", "Venta actualizado con éxito");
        }
    }

    /* PANEL PRODUCTO */

    @FXML
    void cargarTablaProducto() throws SQLException {
        ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

        Statement stmt = bdSeleccionada.createStatement();

        String query = "SELECT * FROM productos";
        String filtro = tfBuscarProducto.getText();
        if (!filtro.equals("")) {
            query += " WHERE " + "nombre LIKE '%" + filtro +
                    "%' OR " + "cantidad LIKE '%" + filtro +
                    "%' OR " + "precioCompra LIKE '%" + filtro +
                    "%' OR " + "precioVenta LIKE '%" + filtro + "%';";
        }

        ResultSet datos = stmt.executeQuery(query);
        while (datos.next()) {
            listaProductos.add(new Producto(datos.getInt("idProducto"), datos.getString("nombre"),
                    datos.getInt("cantidad"), datos.getFloat("precioCompra"),
                    datos.getFloat("precioVenta")));
        }

        tvProductos.setItems(listaProductos);
        cbFormCompraProductos.setItems(listaProductos);
        cbFormVentaProductos.setItems(listaProductos);

        colProductoNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colProductoCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colProductoPrecioCompra.setCellValueFactory(new PropertyValueFactory<>("precioCompra"));
        colProductoPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));

    }

    @FXML
    void creaProducto(){
        cambiarVistaFormProductos();
        editaProducto = false;
    }

    private void cambiarVistaFormProductos() {
        panelProductos.setVisible(false);
        panelFormularioProducto.setVisible(true);
    }

    @FXML
    void modificarProducto() throws SQLException {
        try{

            editaProducto = true;
            idProd = tvProductos.getSelectionModel().getSelectedItem().getIdProducto();
            cargarFormProducto(idProd);
            cambiarVistaFormProductos();
        } catch (NullPointerException npe){
            ventanaDialogo("ERROR", "No hay ningún producto seleccionado");
        }
    }

    private void cargarFormProducto(Integer idProducto) throws SQLException {
        Producto producto = null;
        Statement stmt = bdSeleccionada.createStatement();

        String query = "SELECT * FROM productos WHERE idProducto = '" + idProducto + "'";
        ResultSet datos = stmt.executeQuery(query);

        while(datos.next()){
            producto = new Producto(datos.getInt("idProducto"), datos.getString("nombre"),
                    datos.getInt("cantidad"), datos.getFloat("precioCompra"), datos.getFloat("precioVenta"));
        }

        tfFormProductoNombre.setText(producto.getNombre());
        tfFormProductoCantidad.setText(String.valueOf(producto.getCantidad()));
        tfFormProductoPrecioCompra.setText(String.valueOf(producto.getPrecioCompra()));
        tfFormProductoPrecioVenta.setText(String.valueOf(producto.getPrecioVenta()));
    }

    @FXML
    void borrarProducto() throws SQLException{
        try {
            String id = String.valueOf(tvProductos.getSelectionModel().getSelectedItem().getIdProducto());
            Alert confirmar = ventanaConfirmacion("ELIMINAR PRODUCTO", "Eliminar producto",
                    "¿Está seguro de que desea eliminar este producto?");
            Optional<ButtonType> resultado = confirmar.showAndWait();
            if (resultado.get() == confirmar.getButtonTypes().get(0)) {
                eliminarProducto(id);
                cargarTablaProducto();
            }
        } catch (NullPointerException e) {
            ventanaDialogo("ERROR", "No hay ningún usuario seleccionado");
        }
    }

    private void eliminarProducto(String id) throws SQLException{
        String sql = "DELETE FROM productos WHERE idProducto = ?";
        try (PreparedStatement stmt = bdSeleccionada.prepareStatement(sql)) {
            stmt.setString(1, id);

            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("ELIMINAR PRODUCTO", "Producto eliminado con éxito");
        }
    }

    @FXML
    void aceptarProducto() throws SQLException {
        if (editaProducto){
            actualizaProducto();
        } else {
            insertarProducto();
        }
    }

    private void actualizaProducto() throws SQLException{
        Producto producto = leerValoresProducto();
        producto.setIdProducto(idProd);
        if (!mensajeErrorProducto(producto)){
            consultaActualizarProducto(producto);
            cargarTablaProducto();
            volverProducto();
        }
    }

    private void consultaActualizarProducto(Producto producto) throws SQLException{
        String sql = "UPDATE productos SET nombre=?, cantidad=?, precioCompra=?, precioVenta=? WHERE idProducto=?";
        try (PreparedStatement stmt = bdSeleccionada.prepareStatement(sql)){

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, String.valueOf(producto.getCantidad()));
            stmt.setString(3, String.valueOf(producto.getPrecioCompra()));
            stmt.setString(4, String.valueOf(producto.getPrecioVenta()));
            stmt.setString(5, String.valueOf(producto.getIdProducto()));

            System.out.println(stmt);

            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("ACTUALIZAR PRODUCTO", "Producto actualizado con éxito");
        }


    }

    private void insertarProducto() throws SQLException {
        Producto producto = leerValoresProducto();
        if (!mensajeErrorProducto(producto)){
            consultaInsertarProducto(producto);
            cargarTablaProducto();
            volverProducto();
        }
    }

    private void consultaInsertarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, cantidad, precioCompra, precioVenta) " + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = bdSeleccionada.prepareStatement(sql)) {
            int i = 1;

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, String.valueOf(producto.getCantidad()));
            stmt.setString(3, String.valueOf(producto.getPrecioCompra()));
            stmt.setString(4, String.valueOf(producto.getPrecioVenta()));

            stmt.executeUpdate();// Ejecutar la consulta
            ventanaDialogo("INSERTAR PRODUCTO", "Producto insertado con éxito");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private Producto leerValoresProducto() {
        String nombre = leerCampo("Nombre", tfFormProductoNombre.getText(), ".{1,50}");
        String auxCantidad = leerCampo("Cantidad", tfFormProductoCantidad.getText(), "^[0-9]\\d*(\\.\\d+)?$");
        String auxPrecioCompra = leerCampo("Precio de compra", tfFormProductoPrecioCompra.getText(), "^[0-9]+(\\.[0-9]+)?$");
        String auxPrecioVenta = leerCampo("Precio de venta", tfFormProductoPrecioVenta.getText(), "^[0-9]+(\\.[0-9]+)?$");

        Integer cantidad = null;
        Float precioCompra = null;
        Float precioVenta = null;

        if (auxCantidad != null){
            cantidad = Integer.valueOf(auxCantidad);
        }
        if (auxPrecioCompra != null){
            precioCompra = Float.valueOf(auxPrecioCompra);
        }
        if (auxPrecioVenta != null){
            precioVenta = Float.valueOf(auxPrecioVenta);
        }
        return new Producto(nombre, cantidad, precioCompra, precioVenta);
    }

    // Comprobación de errores en los campos
    private boolean mensajeErrorProducto(Producto producto) {
        boolean hayError = false;
        StringBuilder mensajeError = new StringBuilder();

        hayError = compruebaNombre(producto.getNombre(), mensajeError) ? true : hayError;
        hayError = compruebaCantidad(producto.getCantidad(), mensajeError) ? true : hayError;
        hayError = compruebaPrecio(producto.getPrecioCompra(), mensajeError) ? true : hayError;
        hayError = compruebaPrecio(producto.getPrecioVenta(), mensajeError) ? true : hayError;

        if (hayError) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("CAMPOS ERRÓNEOS");
            error.setContentText("Hay error en los siguientes campos:\n" + mensajeError.toString());
            error.showAndWait();
        }
        return hayError;
    }

    @FXML
    void volverProducto(){
        panelFormularioProducto.setVisible(false);
        tfFormProductoNombre.setText("");
        tfFormProductoCantidad.setText("");
        tfFormProductoPrecioCompra.setText("");
        tfFormProductoPrecioVenta.setText("");
        panelProductos.setVisible(true);
    }



    /* PANEL FACTURACIÓN */

    @FXML
    void borrarFactura() throws SQLException{
        try {
            String id = String.valueOf(tvFacturas.getSelectionModel().getSelectedItem().getIdFactura());
            Alert confirmar = ventanaConfirmacion("ELIMINAR FACTURA", "Eliminar factura",
                    "¿Está seguro de que desea eliminar esta factura?");
            Optional<ButtonType> resultado = confirmar.showAndWait();
            if (resultado.get() == confirmar.getButtonTypes().get(0)) {
                eliminarProducto(id);
                cargarTablaProducto();
            }
        } catch (NullPointerException e) {
            ventanaDialogo("ERROR", "No hay ninguna factura seleccionada");
        }
    }

    @FXML
    void cargarTablaFactura() throws SQLException{
        ObservableList<Factura> listaProductos = FXCollections.observableArrayList();

        Statement stmt = bdSeleccionada.createStatement();

        String query = "SELECT * FROM facturas";
        String filtro = tfBuscarProducto.getText();
        if (!filtro.equals("")) {
            query += " WHERE " + "nombre LIKE '%" + filtro +
                    "%' OR " + "cantidad LIKE '%" + filtro +
                    "%' OR " + "precioCompra LIKE '%" + filtro +
                    "%' OR " + "precioVenta LIKE '%" + filtro + "%';";
        }

        ResultSet datos = stmt.executeQuery(query);
        while (datos.next()) {
            listaProductos.add(new Factura(datos.getInt("idFactura"), datos.getDouble("total"),
                    datos.getInt("idCompra"), datos.getInt("idVenta")));
        }

        tvFacturas.setItems(listaProductos);

        colFacturaID.setCellValueFactory(new PropertyValueFactory<>("idFactura"));
        colFacturaTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colFacturaIDCompra.setCellValueFactory(new PropertyValueFactory<>("idCompra"));
        colFacturaIDVenta.setCellValueFactory(new PropertyValueFactory<>("idVenta"));
    }

    /* INICIALIZACIÓN */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

}
