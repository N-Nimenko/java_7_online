package module.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "piggy_bank")
public class PiggyBank extends  BaseEntity{
    @Column(name = "balance")
    private double balance;

    @ManyToMany(mappedBy = "piggyBanks")
    private Set<Account> accounts;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "PiggyBank{" +
                "balance=" + balance +
                ", accounts=" + accounts +
                '}';
    }
}

