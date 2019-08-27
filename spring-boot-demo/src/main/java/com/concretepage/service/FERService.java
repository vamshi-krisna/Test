package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.Expense;
import com.concretepage.entity.User;

public interface FERService {
	boolean registration(User user);

	boolean addExpense(Expense expense);

	void editExpense(Expense expense);

	void deleteExpense(int expenseId);

	Expense getExpense(int expenseId);

	Iterable<Expense> getExpenses(int userId);

	boolean login(String username, String password);

	Iterable<Expense> expenseReport(int userId, String type, String fromDate, String toDate);


}
