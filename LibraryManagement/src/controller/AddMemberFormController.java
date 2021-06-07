package controller;

import com.jfoenix.controls.JFXTextField;
import db.DB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.BookTM;
import util.MemberTM;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@SuppressWarnings("Duplicates")
public class AddMemberFormController implements Initializable {
    public AnchorPane AnchorPainAddMember;
    public Button btndelete;
    public Button btnsave;
    public TableView<MemberTM> tblNewMember;
    public TableColumn colMemberID;
    public TableColumn colMemberName;
    public TableColumn colMemberAddress;
    public TableColumn colMemberContact;
    public JFXTextField txtMemberID;
    public JFXTextField txtMemberName;
    public JFXTextField txtMemberAddress;
    public Label lblStatus;
    public JFXTextField txtMemberContact;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblNewMember.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("memberID"));
        tblNewMember.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("memberName"));
        tblNewMember.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("memberAddress"));
        tblNewMember.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("memberContact"));

        txtMemberName.setDisable(true);
        txtMemberAddress.setDisable(true);
        txtMemberContact.setDisable(true);
        txtMemberID.setDisable(true);
        btndelete.setDisable(true);
        btnsave.setDisable(true);

        ObservableList<MemberTM> members = FXCollections.observableList(DB.members);
        tblNewMember.setItems(members);

        tblNewMember.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MemberTM>() {
            @Override
            public void changed(ObservableValue<? extends MemberTM> observable, MemberTM oldValue, MemberTM newValue) {
            MemberTM selectedItem = tblNewMember.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btnsave.setText("Save");
                    btndelete.setDisable(true);
                    return;
                }

                btnsave.setText("Update");
                btnsave.setDisable(false);
                btndelete.setDisable(false);
                txtMemberAddress.setDisable(false);
                txtMemberContact.setDisable(false);
                txtMemberName.setDisable(false);
                txtMemberID.setText(selectedItem.getMemberID());
                txtMemberName.setText(selectedItem.getMemberName());
                txtMemberContact.setText(selectedItem.getMemberContact() + "");
                txtMemberAddress.setText(selectedItem.getMemberAddress() + "");
            }

        });
    }


    public void btn_home(ActionEvent actionEvent) throws IOException {
        URL resource=this.getClass().getResource("/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage)this.AnchorPainAddMember.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void btn_save(ActionEvent actionEvent) {

        String name = txtMemberName.getText();
        String contact = txtMemberContact.getText();
        String address = txtMemberAddress.getText();

        if (name.matches("\\b[A-Za-z.\\s]{4,}\\b")) {
            if (contact.matches("\\b[0-9]*\\b")) {
                if (address.matches("\\b[A-Za-z0-9,.\\s]*\\b")) {

                    if (btnsave.getText().equals("Save")) {
                        ObservableList<MemberTM> items = tblNewMember.getItems();
                        items.add(new MemberTM(
                                txtMemberID.getText(),
                                txtMemberName.getText(),
                                txtMemberContact.getText(),
                                txtMemberAddress.getText()
                        ));
                        btn_NewMember(actionEvent);
                    } else {
                        MemberTM selectedItem = tblNewMember.getSelectionModel().getSelectedItem();
                        selectedItem.setMemberName(txtMemberName.getText());
                        selectedItem.setMemberAddress(txtMemberAddress.getText());
                        selectedItem.setMemberContact(txtMemberContact.getText());
                        tblNewMember.refresh();
                        btn_NewMember(actionEvent);
                    }

                }else {
                    txtMemberAddress.requestFocus();
                    System.out.println("Invalid Address");
                }

            }else {
                txtMemberContact.requestFocus();
                System.out.println("Invalid Contact");
            }

        }else {
            txtMemberName.requestFocus();
            System.out.println("Invalid name");
        }
    }

    public void btn_NewMember(ActionEvent actionEvent) {

        txtMemberID.clear();
        txtMemberName.clear();
        txtMemberAddress.clear();
        txtMemberContact.clear();
        tblNewMember.getSelectionModel().clearSelection();
        txtMemberName.setDisable(false);
        txtMemberAddress.setDisable(false);
        txtMemberContact.setDisable(false);
        txtMemberName.requestFocus();
        btnsave.setDisable(false);

        // Generate a new id
        int maxCode = 0;
        for (MemberTM item : DB.members) {
            int code = Integer.parseInt(item.getMemberID().replace("M", ""));
            if (code > maxCode) {
                maxCode = code;
            }
        }
        maxCode = maxCode + 1;
        String code = "";
        if (maxCode < 10) {
            code = "M00" + maxCode;
        } else if (maxCode < 100) {
            code = "M0" + maxCode;
        } else {
            code = "M" + maxCode;
        }
        txtMemberID.setText(code);
    }


    public void btn_delete(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure ?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            MemberTM selectedItem = tblNewMember.getSelectionModel().getSelectedItem();
            tblNewMember.getItems().remove(selectedItem);
        }
    }
}
