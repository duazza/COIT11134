module tropcioassessment2.tropico2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens tropcioassessment2.tropico2 to javafx.fxml;
    exports tropcioassessment2.tropico2;
}
