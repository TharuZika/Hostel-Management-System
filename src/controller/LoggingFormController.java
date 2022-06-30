package controller;

import bo.BOFactory;
import bo.custom.LoginBO;
import bo.custom.ReserveBO;
import bo.impl.LoginBOImpl;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.custom.LoginDAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoggingFormController implements Initializable {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public Label lblError;
    public AnchorPane context;
    public FontAwesomeIconView warningIcon;

    private final LoginBO login = (LoginBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LOGIN);

    public void goToLoginPage(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String UserName=txtUserName.getText();
        String Password=txtPassword.getText();

        if(login.ifUserExists(UserName,Password)){
            URL resource = getClass().getResource("../view/DashboardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) context.getScene().getWindow();
            window.setScene(new Scene(load));
        }
        else{
            lblError.setVisible(true);
            warningIcon.setVisible(true);
        }
    }

    public void goToCanclePage(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void goToPassword(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> allTables=new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        allTables = session.createSQLQuery("SELECT * FROM Login").list();
        if (allTables == null){
            Login login=new Login();
            login.setUserName("user");
            login.setPassword("1234");
            Transaction transaction = session.beginTransaction();
            session.save(login);
            transaction.commit();
        }
        session.close();
    }
}
