package module.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "income_category")
public class IncomeCategory extends BaseEntity {
    @Column(name = "income_type")
    private String incomeType;
    @Column(name = "income_amount")
    private double incomeAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "IncomeCategory{" +
                "incomeType='" + incomeType + '\'' +
                ", incomeAmount=" + incomeAmount +
                '}';
    }
}
