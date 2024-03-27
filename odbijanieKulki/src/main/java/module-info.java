module org.example.odbijaniekulki {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.odbijaniekulki to javafx.fxml;
    exports org.example.odbijaniekulki;
}