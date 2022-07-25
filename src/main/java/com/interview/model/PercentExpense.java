package com.interview.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yash Chaturvedi
 */
public class PercentExpense extends Expense{

    public PercentExpense(double amount, User amountPaidBy, List<Split> splits) {
        super(amount, amountPaidBy, splits);
    }

    @Override
    public boolean verify() {
        double percentSum = 0.0;
        for(Split split : getSplits()) {
            if(!(split instanceof PercentSplit)) {
                return false;
            }
            percentSum += ((PercentSplit) split).getPercent();
        }
        return percentSum == 100.0;
    }
}
