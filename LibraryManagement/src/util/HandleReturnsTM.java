package util;

import java.time.LocalDate;

public class HandleReturnsTM {
    private String issueId;
    private String memberId;
    private String name;
    private LocalDate issuedDate;

    public HandleReturnsTM(String issueId, String memberId, String name, LocalDate issuedDate) {
        this.issueId = issueId;
        this.memberId = memberId;
        this.name = name;
        this.issuedDate = issuedDate;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Override
    public String toString() {
        return "HandleReturnsTM{" +
                "issueId='" + issueId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", issuedDate=" + issuedDate +
                '}';
    }
}
