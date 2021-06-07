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

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class AddBookFormController implements Initializable {
    public AnchorPane AnchorPainAddBook;
    public Button btndelete;
    public Button btnsave;
    public TableView<BookTM> tblNewBook;
    public TableColumn colBookID;
    public TableColumn colBookTitle;
    public TableColumn colBookAuthor;
    public TableColumn colBookStatus;
    public JFXTextField txtBookID;
    public JFXTextField txtBookTitle;
    public JFXTextField txtBookAuthor;
    public Label lblStatus;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblNewBook.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookID"));
        tblNewBook.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
        tblNewBook.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));
        tblNewBook.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("status"));

        txtBookID.setDisable(true);
        txtBookTitle.setDisable(true);
        txtBookAuthor.setDisable(true);
        btndelete.setDisable(true);
        btnsave.setDisable(true);

        ObservableList<BookTM> books = FXCollections.observableList(DB.books);
        tblNewBook.setItems(books);

        tblNewBook.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookTM>() {
            @Override
            public void changed(ObservableValue<? extends BookTM> observable, BookTM oldValue, BookTM newValue) {
                BookTM selectedBook = tblNewBook.getSelectionModel().getSelectedItem();

                if (selectedBook == null){
                    btnsave.setText("Save");
                    btndelete.setDisable(true);
                    return;
                }
                btnsave.setText("Update");
                btnsave.setCancelButton(false);
                btndelete.setDisable(false);
                txtBookAuthor.setDisable(false);
                txtBookID.setDisable(false);
                txtBookTitle.setDisable(false);
                txtBookID.setText(selectedBook.getBookID());
                txtBookTitle.setText(selectedBook.getTitle());
                txtBookAuthor.setText(selectedBook.getAuthor());

            }
        });

    }

    public void btn_save(ActionEvent actionEvent) {

        String author = txtBookAuthor.getText();
        String title = txtBookTitle.getText();

        if (author.matches("\\b[A-Za-z]*\\b")) {
            if (title.matches("\\b[A-Za-z0-9]*\\b")) {

                if (btnsave.getText().equals("Save")) {
                    ObservableList<BookTM> books = tblNewBook.getItems();
                    books.add(new BookTM(
                            txtBookID.getText(),
                            txtBookTitle.getText(),
                            txtBookAuthor.getText(),
                            true));

                    btn_NewBook(actionEvent);
                } else {
                    BookTM selectedItem = tblNewBook.getSelectionModel().getSelectedItem();
                    selectedItem.setTitle(txtBookTitle.getText());
                    selectedItem.setTitle(txtBookAuthor.getText());

                    tblNewBook.refresh();
                }

            }else {
                txtBookTitle.requestFocus();
                System.out.println(" Invalid Title ");
            }

        }else {
            txtBookAuthor.requestFocus();
            System.out.println(" Invalid Name ");
        }

    }

    public void btn_delete(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure ?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            BookTM selectedItem = tblNewBook.getSelectionModel().getSelectedItem();
            tblNewBook.getItems().remove(selectedItem);
        }
    }


    public void btn_NewBook(ActionEvent actionEvent) {

        txtBookAuthor.clear();
        txtBookTitle.clear();
        txtBookID.clear();
        tblNewBook.getSelectionModel().clearSelection();
        txtBookTitle.setDisable(false);
        txtBookAuthor.setDisable(false);
        txtBookTitle.requestFocus();
        btnsave.setDisable(false);

        // Generate a new id
        int maxCode = 0;
        for (BookTM book : DB.books) {
            int code = Integer.parseInt(book.getBookID().replace("B", ""));
            if (code > maxCode) {
                maxCode = code;
            }
        }
        maxCode = maxCode + 1;
        String code = "";
        if (maxCode < 10) {
            code = "B00" + maxCode;
        } else if (maxCode < 100) {
            code = "B0" + maxCode;
        } else {
            code = "B" + maxCode;
        }
        txtBookID.setText(code);
    }







    public void btn_home(ActionEvent actionEvent) throws IOException {
        URL resource=this.getClass().getResource("/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage)this.AnchorPainAddBook.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
