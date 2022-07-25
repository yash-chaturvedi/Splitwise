package com.interview;

import com.interview.factory.ExpenseFactory;
import com.interview.model.EqualSplit;
import com.interview.model.ExactSplit;
import com.interview.model.Expense;
import com.interview.model.ExpenseType;
import com.interview.model.PercentSplit;
import com.interview.model.Split;
import com.interview.model.User;
import com.interview.service.ExpenseService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yash Chaturvedi
 */
public class ExpenseSharingApplication {

    public static void main(String[] args) {
        User raj = new User("raj", "raj@gmail.com", "1234567890");
        User zeref = new User("zeref", "raj@gmail.com", "1234567890");
        User tez = new User("tez", "raj@gmail.com", "1234567890");
        User fay = new User("fay", "raj@gmail.com", "1234567890");
        ExpenseService expenseService = new ExpenseService();
        expenseService.addUser(raj);
        expenseService.addUser(zeref);
        expenseService.addUser(tez);
        expenseService.addUser(fay);

        List<Split> splitList = new ArrayList<>();
        splitList.add(new EqualSplit(raj));
        splitList.add(new EqualSplit(zeref));
        splitList.add(new EqualSplit(tez));
        splitList.add(new EqualSplit(fay));

        expenseService.addExpense(ExpenseType.EQUAL, 1000, raj, splitList);
        expenseService.showBalance();
        System.out.println();

        splitList = new ArrayList<>();
        splitList.add(new ExactSplit(tez, 700));
        splitList.add(new ExactSplit(fay, 300));
        expenseService.addExpense(ExpenseType.EXACT, 1000, raj, splitList);
        expenseService.showBalance();
        System.out.println();

        expenseService.showBalance(fay.getId());
        System.out.println();

        splitList = new ArrayList<>();
        splitList.add(new PercentSplit(raj, 40));
        splitList.add(new PercentSplit(zeref, 20));
        splitList.add(new PercentSplit(tez, 20));
        splitList.add(new PercentSplit(fay, 20));

        expenseService.addExpense(ExpenseType.PERCENT, 1200, zeref, splitList);
        expenseService.showBalance();
        System.out.println();
    }
}
