package util;

import javafx.scene.control.Button;

public class AddBookTM {
    private String bookID;
    private String author;
    private String title;
    private Button btnDelete;

    public AddBookTM(String bookID, String author, String title, Button btnDelete) {
        this.bookID = bookID;
        this.author = author;
        this.title = title;
        this.btnDelete = btnDelete;
    }

    public AddBookTM(String bookID, String author, String title) {
        this.bookID = bookID;
        this.author = author;
        this.title = title;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "AddBookTM{" +
                "bookID='" + bookID + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
