package com.interview.service;

import com.interview.factory.ExpenseFactory;
import com.interview.model.Expense;
import com.interview.model.ExpenseType;
import com.interview.model.Split;
import com.interview.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yash Chaturvedi
 */
public class ExpenseService {
    private final Map<String, User> userIdToDetails;
    private final Map<String, Map<String, Double>> userIdToBalances;

    public ExpenseService() {
        this.userIdToDetails = new HashMap<>();
        this.userIdToBalances = new HashMap<>();
    }

    public void addUser(User user) {
        userIdToDetails.put(user.getId(), user);
        userIdToBalances.put(user.getId(), new HashMap<>());
    }

    public void addExpense(ExpenseType expenseType, double totalAmount, User paidBy, List<Split> splits) {
        Expense expense = ExpenseFactory.create(expenseType, totalAmount, paidBy, splits);
        for(Split split : expense.getSplits()) {
            String paidTo = split.getUser().getId();
            Map<String, Double> balance = userIdToBalances.get(paidBy.getId());
            Double existingBalance = balance.getOrDefault(paidTo, 0.0);
            balance.put(paidTo, existingBalance + split.getAmount());

            balance = userIdToBalances.get(paidTo);
            existingBalance = balance.getOrDefault(paidBy.getId(), 0.0);
            balance.put(paidBy.getId(), existingBalance - split.getAmount());
        }
    }

    public void showBalance() {
        userIdToBalances.forEach((uId, balance) -> {
            showBalance(uId);
        });
    }

    public void showBalance(String uId) {
        Map<String, Double> balance = userIdToBalances.getOrDefault(uId, new HashMap<>());
        balance.forEach((id, amount) -> {
            if(amount > 0) {
                printBalance(id, uId, amount);
            }
        });
    }

    private void printBalance(String id, String uId, Double amount) {
        System.out.println("User " + userIdToDetails.get(id).getName() + " owes " +
                userIdToDetails.get(uId).getName() + " amount " + amount);
    }

}
