package module.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "operation")
public class Operation extends BaseEntity {
    @Column(name = "comment")
    private String comment;
    @Column(name = "amount")
    private double amount;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "receiver_account_number", referencedColumnName = "account_number")
    private Account receiverAccount;
    @ManyToOne
    @JoinColumn(name = "sender_account_number", referencedColumnName = "account_number")
    private Account senderAccount;

    public  String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setComment(String type) {
        this.comment = type;
    }

    public  double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "type='" + comment + '\'' +
                ", amount=" + amount +
                ", receiverUserAccount=" + receiverAccount +
                ", senderUserAccount=" + senderAccount +
                '}';
    }
}
