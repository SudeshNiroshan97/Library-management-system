package util;

public class BookTM {
    private String bookID;
    private String title;
    private String author;
    private boolean status;

    public BookTM(){

    }

    public BookTM(String bookID, String title, String author, boolean status) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.status = status;
    }

    public BookTM clone() {return new BookTM(this.bookID,this.title,this.author,status);}

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookTM{" +
                "bookID='" + bookID + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", status=" + status +
                '}';
    }
}
