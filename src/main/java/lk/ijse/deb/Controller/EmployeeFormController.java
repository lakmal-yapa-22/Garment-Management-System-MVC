package lk.ijse.deb.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.deb.model.Employee;
import lk.ijse.deb.model.Product;
import lk.ijse.deb.model.tm.EmployeeTm;
import lk.ijse.deb.repository.EmployeeRepo;
import lk.ijse.deb.repository.ProductRepo;
import lk.ijse.deb.util.Regex;

import lk.ijse.deb.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;

import java.util.List;

public class EmployeeFormController {

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<String> cmbProductIdFeeId;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colD_O_B;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private Label lblProductName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtD_O_B;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

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
      txtSalary.setText("");
      txtAddress.setText("");
      txtD_O_B.setText("");
      cmbProductIdFeeId.setValue(null);
      searchId.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = EmployeeRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
                clearFields();
                loadAllEmployee();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnProductOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/productPagee.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("product Form");

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        // Fetching data from form fields
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String contactNumber = txtContactNumber.getText().trim();
        String salary = txtSalary.getText().trim();
        String address = txtAddress.getText().trim();
        String birthday = txtD_O_B.getText().trim();
        String ProductId = cmbProductIdFeeId.getValue();

        if (isValied()) {
            boolean isSaved = false;
            try {
                isSaved = EmployeeRepo.save( new Employee(id, name, contactNumber, salary, address, birthday, ProductId));
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
        loadAllEmployee();
    }




  /*
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
       String contactNumber= txtContactNumber.getText();
String salary=txtSalary.getText();
  String address=txtAddress.getText();
String birthday=txtD_O_B.getText();
String ProductId=cmbProductIdFeeId.getValue();

        Employee employee = new Employee(id, name,contactNumber,salary ,address, birthday,ProductId);

        try {
            boolean isSaved = EmployeeRepo.save(employee);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
                clearFields();
                loadAllEmployee();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    */






/*
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
      String contactNumber = txtContactNumber.getText();
      String salary = txtSalary.getText();
      String address= txtAddress.getText();
      String birthday= txtD_O_B.getText();
      String productId= cmbProductIdFeeId.getValue();

        Employee employee = new Employee(id, name,contactNumber,salary,address,birthday,productId );

        try {
            boolean isUpdated = EmployeeRepo.update(employee);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
                clearFields();
                loadAllEmployee();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
*/

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String contactNumber = txtContactNumber.getText().trim();
        String salary = txtSalary.getText().trim();
        String address = txtAddress.getText().trim();
        String birthday = txtD_O_B.getText().trim();
        String productId = cmbProductIdFeeId.getValue();

        // Validate inputs to ensure they are not null or empty
        if (id.isEmpty() || name.isEmpty() || contactNumber.isEmpty() || salary.isEmpty() || address.isEmpty() || birthday.isEmpty() || productId == null) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields.").show();
            return;
        }

        Employee employee = new Employee(id, name, contactNumber, salary, address, birthday, productId);

        try {
            boolean isUpdated = EmployeeRepo.update(employee);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated successfully!").show();
                clearFields();
                loadAllEmployee();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed. Please check your data.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
        }
    }

    @FXML
    void cmbProductIdOnAction(ActionEvent event) {
        String code = cmbProductIdFeeId.getValue();

        try {
            Product product = ProductRepo.searchId(code);
        } catch (SQLException e) {
            throw new RuntimeException(e);

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

            EmployeeTm employee = EmployeeRepo.searchById(id);
            if (employee != null) {

                // Successfully found the employee, set values in text fields
                txtId.setText(employee.getId());
                txtName.setText(employee.getName());
                txtContactNumber.setText(employee.getContactNumber());
                txtSalary.setText(String.valueOf(employee.getSalary()));  // Convert int to String here
                txtAddress.setText(employee.getAddress());
                txtD_O_B.setText(employee.getBirthday());

                cmbProductIdFeeId.setValue(employee.getProductId());



            } else {
                // No employee found, show an alert
                new Alert(Alert.AlertType.INFORMATION,"  "+searchId.getText()+"  :  " + "Employee not found!  "+ "  Enter the correct Employee ID").show();
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
        loadAllEmployee();
        getProductIds();
     //   getSeacrchId();

    }

    private void getSeacrchId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = EmployeeRepo.getId() ;

            for(String id : idList) {
                obList.add(id);
            }

            searchId.setText(obList.toString());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getProductIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = ProductRepo.getCodes() ;

            for(String id : idList) {
                obList.add(id);
            }

            cmbProductIdFeeId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colD_O_B.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
    }

        private void loadAllEmployee() {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<Employee> employeeList = EmployeeRepo.getAll();
            for (Employee employee : employeeList) {
                EmployeeTm tm = new EmployeeTm(
                        employee.getId(),
                        employee.getName(),
                        employee.getContactNumber(),
                        employee.getSalary(),
                        employee.getAddress(),
                        employee.getBirthday(),
                        employee.getProductId()
                );

                obList.add(tm);
            }

            tblEmployee.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDashBoardSupplierOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/supplierPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("supplier Form");
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

    public void btnDashBoardOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/orderPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Order Form");
    }

    public void EmpNameOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtName);

    }
    public boolean isValied(){
        if (!Regex.setTextColor(TextFields.NAME,txtName)) return false;
        if (!Regex.setTextColor(TextFields.IDE,txtId)) return false;
        if (!Regex.setTextColor(TextFields.ADDRESS,txtAddress)) return false;
        if (!Regex.setTextColor(TextFields.SALARY,txtSalary)) return false;
        if (!Regex.setTextColor(TextFields.CONTACT,txtContactNumber)) return false;
        if (!Regex.setTextColor(TextFields.DATE,txtD_O_B)) return false;


        return true;

    }


    public void EmpIdOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.IDE,txtId);

    }

    public void EmpAddressOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }

    public void EmpSalaryOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SALARY,txtSalary);
    }

    public void EmpContactNumberOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CONTACT,txtContactNumber);
    }

    public void EmpBirthdayOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DATE,txtD_O_B);
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

