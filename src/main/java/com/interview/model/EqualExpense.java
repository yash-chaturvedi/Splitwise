package com.interview.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yash Chaturvedi
 */
public class EqualExpense extends Expense{

    public EqualExpense(double amount, User amountPaidBy, List<Split> splits) {
        super(amount, amountPaidBy, splits);
    }

    @Override
    public boolean verify() {
        double amountSum = 0.0;
        for(Split split : getSplits()) {
            if(!(split instanceof EqualSplit)) {
                return false;
            }
            amountSum += split.getAmount();
        }
        return amountSum == getTotalAmount();
    }
}
