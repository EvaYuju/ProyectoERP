module safa.sge_erp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires com.google.gson;


    opens safa.sge_erp to javafx.fxml;
    exports safa.sge_erp;
}