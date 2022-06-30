package controller;

import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ChangeLoginFormController {
    public Pane contentDisplayPane;
    public JFXToggleButton toggleBtn;
    public AnchorPane root;

    public void closeWindowOnAction(ActionEvent actionEvent) {
        javafx.application.Platform.exit();
    }

    public void navigateToLogin(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/LoggingForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void navigateToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/DashboardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void changePaneOnAction(ActionEvent actionEvent) throws IOException {
        if(toggleBtn.isSelected()){
            loadUI("ChangePasswordForm");
        }else{
            loadUI("ChangeUsernameForm");
        }
    }

    public void loadUI(String location) throws IOException {
        contentDisplayPane.getChildren().clear();
        Parent parent= FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        contentDisplayPane.getChildren().add(parent);
    }

    public void initialize() throws IOException {
        loadUI("ChangeUsernameForm");
    }
}
