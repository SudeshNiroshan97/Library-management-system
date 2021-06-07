package util;

public class MemberTM {
    private String memberID;
    private String memberName;
    private String memberAddress;
    private String memberContact;

    public MemberTM(String memberID, String memberName, String memberAddress, String memberContact) {
        this.memberID = memberID;
        this.memberName = memberName;
        this.memberAddress = memberAddress;
        this.memberContact = memberContact;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getMemberContact() {
        return memberContact;
    }

    public void setMemberContact(String memberContact) {
        this.memberContact = memberContact;
    }

    @Override
    public String toString() {
        return "MemberTM{" +
                "memberID='" + memberID + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                ", memberContact='" + memberContact + '\'' +
                '}';
    }
}
