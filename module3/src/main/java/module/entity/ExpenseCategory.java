package module.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "expense_category")
public class ExpenseCategory extends BaseEntity {

    @Column(name = "expense_type")
    private String expenseType;
    @Column(name = "expense_amount")
    private double expenseAmount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "ExpenseCategory{" +
                "expenseType='" + expenseType + '\'' +
                ", expenseAmount=" + expenseAmount +
                '}';
    }

}
