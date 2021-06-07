package util;

import db.IssueDetail;

import java.time.LocalDate;
import java.util.ArrayList;

public class SearchTM {
    private String issueID;
    private LocalDate issueDate;
    private String memberID;
    private ArrayList<IssueDetail>issueDetails;

    public SearchTM(String issueID, LocalDate issueDate, String memberID, ArrayList<IssueDetail> issueDetails) {
        this.issueID = issueID;
        this.issueDate = issueDate;
        this.memberID = memberID;
        this.issueDetails = issueDetails;
    }

    public String getIssueID() {
        return issueID;
    }

    public void setIssueID(String issueID) {
        this.issueID = issueID;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public ArrayList<IssueDetail> getIssueDetails() {
        return issueDetails;
    }

    public void setIssueDetails(ArrayList<IssueDetail> issueDetails) {
        this.issueDetails = issueDetails;
    }

    @Override
public String toString() {
        return "Issue{" +
                "issueID='" + issueID + '\'' +
                ", issueDate=" + issueDate +
                ", memberID='" + memberID + '\'' +
                ", issueDetails=" + issueDetails +
                '}';
    }
}
