package com.concretepage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.entity.Expense;
import com.concretepage.entity.User;
import com.concretepage.repository.ExpenseRepository;
import com.concretepage.repository.UserRepository;


@Service
public class FERServiceImpl implements FERService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public synchronized boolean registration(User user) {

		User user1 = userRepository.save(user);
		return user1 != null ? true : false;
	}

	@Override
	public boolean login(String username, String password) {
		List<User> list = userRepository.findByUsernameAndPassword(username, password);
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public synchronized boolean addExpense(Expense expense) {

		Expense expense1 = expenseRepository.save(expense);
		return expense1 != null ? true : false;

	}

	@Override
	public void editExpense(Expense expense) {
		expenseRepository.save(expense);

	}

	@Override
	public void deleteExpense(int expenseId) {
		expenseRepository.delete(getExpense(expenseId));

	}

	@Override
	public Expense getExpense(int expenseId) {
		Expense expense = expenseRepository.findById(expenseId).get();
		return expense;
	}

	@Override
	public Iterable<Expense> getExpenses(int userId) {
		Iterable<Expense> expenses = expenseRepository.findByUserId(userId);
		return expenses;
	}

	@Override
	public Iterable<Expense> expenseReport(int userId, String type, String fromDate, String toDate) {
		Iterable<Expense> expenses = expenseRepository.fetchExpenses(userId, type, fromDate, toDate);
		return expenses;
	}

}