package controller;

import com.jfoenix.controls.JFXButton;
import db.DB;
import db.IssueDetail;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.*;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class IssueBookFormController implements Initializable {
    public AnchorPane anchorPaneIssueBook;
    public TextField txtMemberName;
    public ComboBox cmb_memberID;
    public Button btnhome;
    public Button btn_addBook;
    public Label txtIssueID;
    public Label txtDate;
    public TableView<AddBookTM> tbl_IssueBook;
    public TableColumn tblCol_BookID;
    public TableColumn tblCol_Title;
    public TableColumn tblCol_Author;
    public TableColumn tblCol_Action;
    public TableColumn tblCol_total;
    public TableColumn tblCol_delete;
    public ComboBox cmb_BookID;
    public TextField txt_title;
    public TextField txt_Author;
    public Button btnAdd;
    public Button btnIssueBook;
    public Label txtNumberOfBooks;

    ObservableList<AddBookTM> addBook = FXCollections.observableArrayList();

    int count = 0;

    private ArrayList<BookTM> tempBooks = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmb_BookID.setDisable(true);
        cmb_memberID.setDisable(true);
        txtMemberName.setDisable(true);
        txt_Author.setDisable(true);
        txt_title.setDisable(true);
        btnAdd.setDisable(true);
        generateIssueId();

        //Set date

        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yy");
        Date date = new Date();

        txtDate.setText(sf.format(date));

        //Map columns with table model
        tbl_IssueBook.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookID"));
        tbl_IssueBook.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
        tbl_IssueBook.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));
        tbl_IssueBook.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        //load member id's
        ObservableList<String> members = cmb_memberID.getItems();
        for (MemberTM member : DB.members) {
            members.add(member.getMemberID());
        }

        //load book id's
        ObservableList<String> books = cmb_BookID.getItems();
        for (BookTM book : DB.books) {
            if (book.isStatus()) {

                books.add(book.getBookID());
            }

        }

        //create temporary Book list
        tempBooks = new ArrayList<>();
        for (BookTM book : DB.books) {
            tempBooks.add(book.clone());
        }

        //when member id is selected
        cmb_memberID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                                                                                @Override
                                                                                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                                                                    String selectedMemberID = (String) cmb_memberID.getSelectionModel().getSelectedItem();
                                                                                    enableIssueBookButton();

                                                                                    for (MemberTM member : DB.members) {
                                                                                        if (member.getMemberID().equals(selectedMemberID)) {
                                                                                            txtMemberName.setText(member.getMemberName());
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
        );


        //when book ID is selected
        cmb_BookID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String selectedBookId = (String) cmb_BookID.getSelectionModel().getSelectedItem();

                if (selectedBookId == null) {
                    txtMemberName.clear();
                    txt_Author.clear();
                    txt_title.clear();
                    btnAdd.setDisable(true);
                    return;
                }
                btnAdd.setDisable(false);
                for (BookTM book : tempBooks) {
                    if (book.getBookID().equals(selectedBookId)) {
                        txt_title.setText(book.getTitle());
                        txt_Author.setText(book.getAuthor());

                    }
                }

            }
        });


    }

    private void enableIssueBookButton() {
        String selectedCustomer = (String) cmb_memberID.getSelectionModel().getSelectedItem();
        int size = tbl_IssueBook.getItems().size();
        if (selectedCustomer == null || size == 0) {
            btnIssueBook.setDisable(true);
        } else {
            btnIssueBook.setDisable(false);
        }
    }

    private void reset() {
        // Initialize controls to their default states
        txtDate.setText(LocalDate.now() + "");

        btnIssueBook.setDisable(true);
        btnAdd.setDisable(false);
        txt_Author.setDisable(false);
        txt_Author.setDisable(false);
        txt_title.setDisable(false);
        txtMemberName.setDisable(false);
        cmb_memberID.setDisable(false);
        cmb_BookID.setDisable(false);
        txt_Author.clear();
        txt_title.setEditable(false);
        txtMemberName.setEditable(false);
        cmb_memberID.getSelectionModel().clearSelection();
        cmb_BookID.getSelectionModel().clearSelection();
        tbl_IssueBook.getItems().clear();
        txtNumberOfBooks.setText("Number of Books : 0.00");
    }

    public void btnAddBook_OnAction(ActionEvent actionEvent) {
        reset();
    }

    public void btn_Add(ActionEvent actionEvent) {
        String title = txt_title.getText();
        String author = txt_Author.getText();

        String selectedBookID = (String) cmb_BookID.getSelectionModel().getSelectedItem();
        ObservableList<AddBookTM> details = tbl_IssueBook.getItems();


        JFXButton btnDelete = new JFXButton("Delete");

        AddBookTM addBook = new AddBookTM(
                (String) cmb_BookID.getSelectionModel().getSelectedItem(),
                txt_Author.getText(),
                txt_title.getText(),
                btnDelete
        );

        count++;

        System.out.println("before delete : " + addBook);

        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tbl_IssueBook.getItems().remove(addBook);
                tbl_IssueBook.refresh();
            }
        });

        txtNumberOfBooks.setText(count + "");

        details.add(addBook);
        tbl_IssueBook.setItems(details);
        tbl_IssueBook.refresh();
        enableIssueBookButton();
    }


    public void btn_IssueBook_OnAction(ActionEvent actionEvent) {

        int ordersCount = 0;
        ArrayList<IssueDetail> details = new ArrayList<>();

        for (AddBookTM item : tbl_IssueBook.getItems()) {
            details.add(new IssueDetail(
                    item.getBookID(),
                    item.getTitle(),
                    item.getAuthor()
            ));

            for (BookTM book : DB.books) {
                if (item.getBookID().equals(book.getBookID())) {
                    book.setStatus(false);
                }

            }
        }

        SearchTM searchTMBook = new SearchTM(
                txtIssueID.getText(),
                LocalDate.parse(txtDate.getText()),
                (String) cmb_memberID.getSelectionModel().getSelectedItem(),
                details
        );

        String memberName = null;
        for (MemberTM member : DB.members) {
            if (member.getMemberID().equals(cmb_memberID.getSelectionModel().getSelectedItem())) {
                memberName = member.getMemberName();
            }
        }

        HandleReturnsTM handleReturnsTM = new HandleReturnsTM(
                txtIssueID.getText(),
                (String) cmb_memberID.getSelectionModel().getSelectedItem(),
                memberName,
                LocalDate.parse(txtDate.getText())
        );


        DB.searchTMS.add(searchTMBook);

        DB.handleReturns.add(handleReturnsTM);

        tbl_IssueBook.getItems().clear();
        txtNumberOfBooks.setText("");
        cmb_memberID.getSelectionModel().clearSelection();
        txtMemberName.clear();

        ObservableList<String> items = cmb_BookID.getItems();
        items.clear();

        for (BookTM book : DB.books) {
            if (book.isStatus()) {
                items.add(book.getBookID());
            }

        }

        generateIssueId();


    }

    public void btn_home(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) this.anchorPaneIssueBook.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void generateIssueId() {
        int maxIssueId = 0;
        for (SearchTM searchTM : DB.searchTMS) {
            int orderId = Integer.parseInt(searchTM.getIssueID().replace("OD", ""));
            if (orderId > maxIssueId) {
                maxIssueId = orderId;
            }
        }
        maxIssueId++;
        if (maxIssueId < 10) {
            txtIssueID.setText("OD00" + maxIssueId);
        } else if (maxIssueId < 100) {
            txtIssueID.setText("OD0" + maxIssueId);
        } else {
            txtIssueID.setText("OD" + maxIssueId);
        }
    }

}
