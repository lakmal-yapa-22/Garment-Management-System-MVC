package lk.ijse.deb.Controller;

import animatefx.animation.SlideInDown;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.deb.Mail;
import lk.ijse.deb.db.DbConnection;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LoginFormController {

    @FXML
    private Button btnCreateAccount;

    @FXML
    private Button btnLogin;

    @FXML
    private AnchorPane root;

    @FXML
    private Text txtForgetPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        try {
            checkCredential(userName, password);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkCredential(String userName, String password) throws SQLException, IOException {
        String sql = "select username, password from Admin where username = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userName);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String dbPw = resultSet.getString("password");
            if (password.equals(dbPw)) {
                sendLoginNotification(userName);
                navigateToTheDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "Sorry! Password is incorrect!").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Sorry! User ID can't be found!").show();
        }
    }

    private void sendLoginNotification(String userName) {
        Mail mail = new Mail();
        mail.setTo("recipient@example.com"); // Change to the recipient's email
        mail.setSubject("Login Notification");
        mail.setMsg("Welcome! User: " + userName + "\nNew Login Detected at: " + java.time.LocalDateTime.now() + "\nThank You,\nstarGarment");

        Thread mailThread = new Thread(mail);
        mailThread.start();
    }

    public void navigateToTheDashboard() throws IOException {
        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
        new SlideInDown(root).play();
    }

    @FXML
    void signUpOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/signUpPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Sign Up Form");
    }

    @FXML
    void txtForgetPasswordAction(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/forgetPassword.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Forget Form");
    }

    private void setTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        lblTime.setText(now.format(formatter));
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    public void initialize() {
        setDate();
        setTime();
    }

    public void btnOnclick(MouseEvent mouseEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/welcomePage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Welcome Page");
    }
}
/*package lk.ijse.deb.Controller;

import animatefx.animation.FadeIn;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInLeft;
import com.sun.nio.sctp.Notification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.deb.Mail;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.tm.AdminTm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LoginFormController {

    @FXML
    private Button btnCreateAccount;

    @FXML
    private Button btnLogin;

    @FXML
    private AnchorPane root;

    @FXML
    private Text txtForgetPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;


    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String userName=txtUserName.getText();
        String password=txtPassword.getText();

        try {
            checkCredential(userName,password);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void checkCredential(String userName, String password) throws SQLException, IOException {
        String sql = "select username, password from Admin where username =? ";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userName);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String dbPw = resultSet.getString("password");
            if(password.equals(dbPw)) {

navigateToTheDashboard();



            } else {
                new Alert(Alert.AlertType.ERROR, "sorry! password is incorrect!").show();
            }

        } else {
            new Alert(Alert.AlertType.INFORMATION, "sorry! user id can't be find!").show();
        }
    }

    public void navigateToTheDashboard() throws IOException {
        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
        new SlideInDown(root).play();


    }





    @FXML
    void signUpOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/signUpPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("sinUp Form");

    }

    @FXML
    void txtForgetPasswordAction(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/forgetPassword.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("forget Form");

    }
    private void setTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        lblTime.setText(now.format(formatter));
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }
    public void initialize() {
        setDate();
        setTime();

    }

    public void btnOnclick(MouseEvent mouseEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/welcomePage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("welcome Page");
    }
}
*/