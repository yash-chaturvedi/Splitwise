package com.interview.factory;

import com.interview.model.EqualExpense;
import com.interview.model.ExactExpense;
import com.interview.model.Expense;
import com.interview.model.ExpenseType;
import com.interview.model.PercentExpense;
import com.interview.model.PercentSplit;
import com.interview.model.Split;
import com.interview.model.User;

import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.util.List;

/**
 * @author Yash Chaturvedi
 */
public class ExpenseFactory {
    private static final DecimalFormat df = new DecimalFormat("#.##");

    public static Expense create(ExpenseType expenseType, double totalAmount, User paidBy, List<Split> splits) {
        switch (expenseType) {
            case EQUAL:
                int totalSplits = splits.size();
                double splitAmount = totalAmount / totalSplits;
                splitAmount = Double.parseDouble(df.format(splitAmount));
                for(Split split : splits) {
                    split.setAmount(splitAmount);
                }
                splits.get(0).setAmount(splitAmount + (totalAmount - splitAmount * totalSplits));
                return new EqualExpense(totalAmount, paidBy, splits);
            case EXACT:
                return new ExactExpense(totalAmount, paidBy, splits);
            case PERCENT:
                for(Split split : splits) {
                    double amount = totalAmount * ((PercentSplit) split).getPercent() / 100.0;
                    amount = Double.parseDouble(df.format(amount));
                    split.setAmount(amount);
                }
                return new PercentExpense(totalAmount, paidBy, splits);
            default: throw new InvalidParameterException("Invalid expense type");
        }
    }
}
