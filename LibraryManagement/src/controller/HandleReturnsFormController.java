package controller;

import db.DB;
import db.IssueDetail;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.HandleReturnsTM;
import util.MemberTM;
import util.SearchTM;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class HandleReturnsFormController implements Initializable {
    public AnchorPane anchorPaneSearch;
    public TableView<HandleReturnsTM> tbl_search;
    public TableColumn tbl_searchIssueID;
    public TableColumn tbl_searchMemberID;
    public TableColumn tbl_searchName;
    public TableColumn tbl_searchIssuedDate;
    public TextField txtSearch;
    public TextField txtMemberName;
    public ComboBox cmb_memberID;
    public TextField txtCurrentDate;
    public TextField txtIssuedDate;
    public TextField txtFee;

    ObservableList<HandleReturnsTM> list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<HandleReturnsTM> list = tbl_search.getItems();

        System.out.println(DB.searchTMS);

        tbl_search.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("issueId"));
        tbl_search.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("memberId"));
        tbl_search.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_search.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("issuedDate"));
        ArrayList<IssueDetail> details = new ArrayList<>();

        for (HandleReturnsTM handleReturn : DB.handleReturns) {
            list.add(handleReturn);
        }

        tbl_search.setItems(list);

        //set current date
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        txtCurrentDate.setText(sf.format(date));

        ObservableList<String> members = cmb_memberID.getItems();
        for (HandleReturnsTM handleReturn : DB.handleReturns) {
            members.add(handleReturn.getMemberId());
        }

        cmb_memberID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String selectedMemberID = (String) cmb_memberID.getSelectionModel().getSelectedItem();
                for (HandleReturnsTM handleReturns : DB.handleReturns) {
                    if (handleReturns.getMemberId().equals(selectedMemberID)) {
                        txtMemberName.setText(handleReturns.getName());
                        txtIssuedDate.setText(handleReturns.getIssuedDate().toString());
                        break;
                    }
                }
            }
        });
}

    public void tblOnMouseClik(MouseEvent mouseEvent) {

    }

    public void btn_CalculateFee_onAction(ActionEvent actionEvent) {

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String issueDate = txtIssuedDate.getText();
        String currentDate = txtCurrentDate.getText();

        int validDate = 14;
        double fee = 0;
        try {
            Date dateBefore = myFormat.parse(issueDate);
            Date dateAfter = myFormat.parse(currentDate);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            float daysBetween = (difference / (1000*60*60*24));
            System.out.println("Dates Between " +daysBetween);

            if (daysBetween>validDate){
                int extentndceDays = (int) daysBetween - validDate;
                fee = extentndceDays*15;
            }
            txtFee.setText(fee+"");

        }catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void btn_home(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) this.anchorPaneSearch.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

