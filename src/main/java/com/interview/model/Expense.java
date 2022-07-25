package com.interview.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author Yash Chaturvedi
 */
public abstract class Expense {

    private final String id;
    private double totalAmount;
    private User amountPaidBy;
    private List<Split> splits;

    public Expense(double amount, User amountPaidBy, List<Split> splits) {
        this.id = UUID.randomUUID().toString();
        this.totalAmount = amount;
        this.amountPaidBy = amountPaidBy;
        this.splits = splits;
    }

    public String getId() {
        return id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getAmountPaidBy() {
        return amountPaidBy;
    }

    public void setAmountPaidBy(User amountPaidBy) {
        this.amountPaidBy = amountPaidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public abstract boolean verify();
}
