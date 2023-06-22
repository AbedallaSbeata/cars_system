module com.mycompany.project_database {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.mycompany.project_database to javafx.fxml;
    exports com.mycompany.project_database;
}
