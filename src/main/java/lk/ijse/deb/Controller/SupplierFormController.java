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
import lk.ijse.deb.model.Supplier;
import lk.ijse.deb.model.tm.SupplierTm;
import lk.ijse.deb.repository.SupplierRepo;
import lk.ijse.deb.util.Regex;
import lk.ijse.deb.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SupplierFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private Text txtWelcome;

    @FXML
    private TextField searchId;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtContactNumber.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        searchId.setText("");


    }




    public void btnDashBoardEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/employeePage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("buyer Form");
    }




    public void btnDashBoardOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/orderPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Order Form");
    }



    public void btnDashBoardProductOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/productPagee.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("product Form");
    }

    public void btnDashBoardRowMatirialOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/rowMatirialPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("row matirial Form");
    }

    public void btnDashBoardBuyerOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/buyerDetailPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Buyer Form");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = SupplierRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier deleted!").show();
                clearFields();
                loadAllSupplier();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String contactNumber = txtContactNumber.getText().trim();
        String email = txtEmail.getText().trim();
        String address = txtAddress.getText().trim();

        if (isValied()) {
            boolean isSaved = false;
            try {
                isSaved = SupplierRepo.save( new Supplier(id, name, contactNumber,email,address));
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
        loadAllSupplier();
    }





    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String contactNumber = txtContactNumber.getText().trim();
        String email = txtEmail.getText().trim();
        String address = txtAddress.getText().trim();

        // Check for empty fields before proceeding with the update
        if (id.isEmpty() || name.isEmpty() || contactNumber.isEmpty() || email.isEmpty() || address.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "All fields must be filled. Please check your inputs.").show();
        } else {
            Supplier supplier = new Supplier(id, name, contactNumber, email, address);

            try {
                boolean isUpdated = SupplierRepo.update(supplier);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated successfully!").show();
                    clearFields();
                    loadAllSupplier();
                } else {
                    new Alert(Alert.AlertType.WARNING, "No changes were made to the supplier.").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error updating supplier: " + e.getMessage()).show();
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

            SupplierTm supplier = SupplierRepo.searchById(id);
            if (supplier != null) {

                txtId.setText(supplier.getId());
                txtName.setText(supplier.getName());
                txtContactNumber.setText(supplier.getContactNumber());
                txtEmail.setText(supplier.getEmail());
                txtAddress.setText(supplier.getAddress());



            } else {
                // No employee found, show an alert
                new Alert(Alert.AlertType.INFORMATION,"  "+searchId.getText()+"  :  " + "Supplier not found!  "+ "  Enter the correct supplier ID").show();
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







    /*
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
        }*/


    public void initialize() {
        setCellValueFactory();
        loadAllSupplier();
    }

    private void loadAllSupplier() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<Supplier> supList = SupplierRepo.getAll();
            for (Supplier supplier : supList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getContactNumber(),
                        supplier.getEmail(),
                        supplier.getAddress()
                );

                obList.add(tm);
            }

            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));


    }


    public void SupEmailOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EMAIL,txtEmail);
    }

    public void SupContactOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CONTACT,txtContactNumber);
    }

    public void SupNameKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtName);
    }

    public void SupAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }

    public void SupIdOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.IDS,txtId);
    }
    public boolean isValied(){
        if (!Regex.setTextColor(TextFields.IDS,txtId)) return false;
        if (!Regex.setTextColor(TextFields.NAME,txtName)) return false;
        if (!Regex.setTextColor(TextFields.CONTACT,txtContactNumber)) return false;
        if (!Regex.setTextColor(TextFields.EMAIL,txtEmail)) return false;
        if (!Regex.setTextColor(TextFields.ADDRESS,txtAddress)) return false;



        return true;

    }

    public void btnDashBoardPaymentOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/payment.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("payment Form");
    }

    public void DashBoardDashBoardOnClick(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }
}
