package controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class DashboardFormController {
    public AnchorPane root;
    public FontAwesomeIconView btnSettings;
    public FontAwesomeIconView btnStudent;
    public FontAwesomeIconView btnRoom;
    public FontAwesomeIconView btnKeyMoney;
    public FontAwesomeIconView btnReserve;

    public void initialize() throws IOException {
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/ReserveForm.fxml")));
    }

    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof FontAwesomeIconView) {
            FontAwesomeIconView btn = (FontAwesomeIconView) event.getSource();


            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), btn);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.LAWNGREEN);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            btn.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof FontAwesomeIconView) {
            FontAwesomeIconView btn = (FontAwesomeIconView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), btn);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            btn.setEffect(null);
        }
    }

    public void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof FontAwesomeIconView) {
            FontAwesomeIconView btn = (FontAwesomeIconView) event.getSource();

            Parent root = null;

            switch (btn.getId()) {
                case "btnStudent":
                    root = FXMLLoader.load(this.getClass().getResource("/view/StudentForm.fxml"));
                    break;
                case "btnRoom":
                    root = FXMLLoader.load(this.getClass().getResource("/view/RoomForm.fxml"));
                    break;
                case "btnKeyMoney":
                    root = FXMLLoader.load(this.getClass().getResource("/view/KeyMoneyForm.fxml"));
                    break;
                case "btnSettings":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ChangeLoginForm.fxml"));
                    break;
                case "btnReserve":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ReserveForm.fxml"));
                    break;
            }

            if (root != null) {
                this.root.getChildren().clear();
                this.root.getChildren().add(root);

            }
        }
    }
}
