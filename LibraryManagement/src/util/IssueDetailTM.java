package util;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class IssueDetailTM {

    private String issueID;
    private String memberID;
    private String issueDate;
    private ArrayList<BookTM>boolDetails;

    public IssueDetailTM(String issueID, String memberID, String issueDate, ArrayList<BookTM> boolDetails) {
        this.issueID = issueID;
        this.memberID = memberID;
        this.issueDate = issueDate;
        this.boolDetails = boolDetails;
    }

    public String getIssueID() {
        return issueID;
    }

    public void setIssueID(String issueID) {
        this.issueID = issueID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public ArrayList<BookTM> getBoolDetails() {
        return boolDetails;
    }

    public void setBoolDetails(ArrayList<BookTM> boolDetails) {
        this.boolDetails = boolDetails;
    }

    @Override
    public String toString() {
        return "IssueDetailTM{" +
                "issueID='" + issueID + '\'' +
                ", memberID='" + memberID + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", boolDetails=" + boolDetails +
                '}';
    }
}
