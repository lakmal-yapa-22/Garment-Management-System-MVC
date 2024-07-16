package lk.ijse.deb.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.deb.model.Buyer;
import lk.ijse.deb.model.tm.BuyerTm;
import lk.ijse.deb.repository.BuyerRepo;
import lk.ijse.deb.util.Regex;
import lk.ijse.deb.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BuyerAdmin {
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCompanyName;

    @FXML
    private TableColumn<?, ?> colContacNumber;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BuyerTm> tblBuyer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private Text txtWelcome;
    @FXML
    private TextField searchId;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/buyerDetailPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("buyer Form");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtContactNumber.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtCompanyName.setText("");

    }







    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = BuyerRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "buyer deleted!").show();
                loadAllBuyer();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText().trim(); // Trim to remove unnecessary spaces
        String tel = txtContactNumber.getText().trim();
        String address = txtAddress.getText().trim();
        String email = txtEmail.getText().trim();
        String company = txtCompanyName.getText().trim();

        if (isValied()) {
            boolean isSaved = false;
            try {
                isSaved = BuyerRepo.save( new Buyer(id, tel,address,email,company));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();

            }

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                clearFields();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }
        loadAllBuyer();
    }




/*
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String tel = txtContactNumber.getText();
        String address = txtAddress.getText();
        String email =txtEmail.getText();
       String company=txtCompanyName.getText();
        Buyer buyer = new Buyer(id, tel,address,email,company);

        try {
            boolean isSaved = Buyer.save(buyer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                clearFields();
                loadAllBuyer();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    */



    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText().trim(); // Trim to remove unnecessary spaces
        String tel = txtContactNumber.getText().trim();
        String address = txtAddress.getText().trim();
        String email = txtEmail.getText().trim();
        String company = txtCompanyName.getText().trim();

        // Check if any required field is empty
        if (id.isEmpty() || tel.isEmpty() || address.isEmpty() || email.isEmpty() || company.isEmpty()) {
            // Display an alert if any field is empty
            new Alert(Alert.AlertType.WARNING, "All fields are required. Please ensure no fields are empty.").show();
        } else {
            // Proceed with creating the buyer object and updating it
            Buyer buyer = new Buyer(id, tel, address, email, company);

            try {
                boolean isUpdated = BuyerRepo.update(buyer);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer updated successfully!").show();
                    loadAllBuyer();
                    clearFields();
                } else {
                    // This alert can be used to notify that updating was unsuccessful
                    new Alert(Alert.AlertType.ERROR, "Failed to update the buyer. Please check the data.").show();
                }
            } catch (SQLException e) {
                // Show a detailed error message from the exception
                new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
            }
        }
    }




    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = searchId.getText().trim();  // Always trim input to remove unwanted spaces

        try {
            if (id.isEmpty()) {
                clearFields();
                return; // No need for break here, return to exit the method
            }

            Buyer buyer = BuyerRepo.searchById(id);
            if (buyer != null) {

                txtId.setText(buyer.getId());
                txtContactNumber.setText(buyer.getTel());
                txtAddress.setText(buyer.getAddress());
                txtEmail.setText(buyer.getEmail());
                txtCompanyName.setText(buyer.getCompany());



            } else {
                // No employee found, show an alert
                new Alert(Alert.AlertType.INFORMATION,"  "+searchId.getText()+"  :  " + "Buyer not found!  "+ "  Enter the correct Buyer ID").show();
                searchId.setText("");
                clearFields();
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions here
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
            searchId.setText("");
            clearFields();
        } catch (Exception e) {
            // Handle any other exceptions here
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
            searchId.setText("");
            clearFields();
        }
    }




    public void initialize() {
        setCellValueFactory();
        loadAllBuyer();
    }

    private void loadAllBuyer() {
        ObservableList<BuyerTm> obList = FXCollections.observableArrayList();

        try {
            List<Buyer> buyerList = BuyerRepo.getAll();
            for (Buyer buyer :buyerList) {
                BuyerTm tm = new BuyerTm(
                        buyer.getId(),
                        buyer.getTel(),
                        buyer.getAddress(),
                        buyer.getEmail(),
                        buyer.getCompany()
                );

                obList.add(tm);
            }

            tblBuyer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colContacNumber.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("company"));
    }


    public void BuyerIDOnKeyRelesed(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.IDB,txtId);
    }

    public void BuyerContactOnKeyRelesed(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CONTACT,txtContactNumber);

    }

    public void BuyerAddressOnKeyRelesed(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }

    public void BuyerCompanyNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtCompanyName);
    }

    public void BuyerEmailOnKeyEmail(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EMAIL,txtEmail);
    }

    public boolean isValied(){
        if (!Regex.setTextColor(TextFields.ADDRESS,txtCompanyName)) return false;
        if (!Regex.setTextColor(TextFields.IDB,txtId)) return false;
        if (!Regex.setTextColor(TextFields.EMAIL,txtEmail)) return false;
        if (!Regex.setTextColor(TextFields.ADDRESS,txtAddress)) return false;
        if (!Regex.setTextColor(TextFields.CONTACT,txtContactNumber)) return false;




        return true;


    }

}









