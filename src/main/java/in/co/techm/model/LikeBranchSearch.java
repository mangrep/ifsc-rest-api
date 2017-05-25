package in.co.techm.model;

import java.io.Serializable;


public class LikeBranchSearch implements Serializable {
    private String bankName;
    private String branchName;
    private String source;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "LikeBranchSearch{" +
                "bankName='" + bankName + '\'' +
                ", branchName='" + branchName + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
