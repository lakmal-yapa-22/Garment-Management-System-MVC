package lk.ijse.deb.Controller.Buyer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.util.Regex;
import lk.ijse.deb.util.TextFields;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BuyerSignUpControllerr {
    @FXML
    private Button btnsign;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtEmail;

    @FXML
    private Text txtLogin;

    @FXML
    private TextField txtUseName;

    @FXML
    private TextField txtconformPassword;

    @FXML
    private PasswordField txtpassword;

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        // Retrieve user input
        String userId = txtUseName.getText();
        String password = txtpassword.getText();
        String confirmPW = txtconformPassword.getText();
        String email = txtEmail.getText();
        String type = "Admin";

        // Check if passwords match
        if (password.equals(confirmPW)) {
            try {
                // Validate input fields
                if (isValied()) {
                    // Attempt to save user
                    boolean isSaved = saveUser(email, userId, password, type);
                    if (isSaved) {
                        // Show confirmation alert and clear fields
                        new Alert(Alert.AlertType.CONFIRMATION, "Your sign-up is complete.").show();
                        clearFields();

                    } else {
                        // Show error alert if failed to sign up
                        new Alert(Alert.AlertType.ERROR, "Failed to sign up. Please try again.").show();
                        clearFields();
                    }
                }
            } catch (SQLException e) {
                // Show error alert for database errors
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            // Show warning alert if passwords do not match
            new Alert(Alert.AlertType.WARNING, "Passwords do not match. Please try again.").show();
            txtconformPassword.setText(""); // Clear confirmation password field
        }
    }

    private boolean saveUser(String email, String username, String password, String type) throws SQLException {
        String sql = "INSERT INTO AdminBuyer (email,username,password,type) VALUES (?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, email);
        pstm.setString(2, username);
        pstm.setString(3, password);
        pstm.setString(4, type);

        return pstm.executeUpdate() > 0;
    }

    @FXML
    void txtLoginOnMouseClickedAction(MouseEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/Buyer/loginBuyerPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }

    public void userNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME, txtUseName);
    }

    public void passwordOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PASSWORD, txtpassword);
    }

    public void emailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EMAIL, txtEmail);
    }

    public boolean isValied() {
        if (!Regex.setTextColor(TextFields.NAME, txtUseName)) return false;
        if (!Regex.setTextColor(TextFields.PASSWORD, txtpassword)) return false;
        if (!Regex.setTextColor(TextFields.EMAIL, txtEmail)) return false;


        return true;

    }
    private void clearFields() {
        txtEmail.setText("");
        txtpassword.setText("");
        txtconformPassword.setText("");
        txtUseName.setText("");
    }

}

