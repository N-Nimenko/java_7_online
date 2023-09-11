package module.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "account")
public class Account extends BaseEntity {
    @Column(name = "balance")
    private double balance;
    @Column(name = "account_number")
    private int accountNumber;

    @ManyToMany(mappedBy = "accounts")
    private Set<User> users;

    @ManyToMany
    @JoinTable(
            name = "account_piggyBank",
            joinColumns = @JoinColumn(name = "account_number"),
            inverseJoinColumns = @JoinColumn(name = "piggyBank_id")
    )
    private Set<PiggyBank> piggyBanks;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Set<PiggyBank> getPiggyBanks() {
        return piggyBanks;
    }

    public void setPiggyBanks(Set<PiggyBank> piggyBanks) {
        this.piggyBanks = piggyBanks;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
