package com.concretepage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.concretepage.entity.Expense;
import com.concretepage.entity.User;
import com.concretepage.service.FERService;

@Controller
@RequestMapping("fer")
public class FERController {
	@Autowired
	private FERService ferService;

	@PostMapping("registration")
	public ResponseEntity<Void> registration(@ModelAttribute User user, UriComponentsBuilder builder) {
		boolean flag = ferService.registration(user);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/registration/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PostMapping("addExpense")
	public ResponseEntity<Void> addExpense(@ModelAttribute Expense expense, UriComponentsBuilder builder) {
		boolean flag = ferService.addExpense(expense);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/addExpense/{id}").buildAndExpand(expense.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("editExpense")
	public ResponseEntity<Expense> editExpense(@RequestBody Expense expense) {
		ferService.editExpense(expense);
		return new ResponseEntity<Expense>(expense, HttpStatus.OK);
	}

	@DeleteMapping("deleteExpense/{id}")
	public ResponseEntity<Void> deleteExpense(@PathVariable("id") Integer id) {
		ferService.deleteExpense(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("getExpense/{expenseid}")
	public ResponseEntity<Expense> getExpense(@PathVariable("expenseid") Integer expenseId) {
		Expense expense = ferService.getExpense(expenseId);
		return new ResponseEntity<Expense>(expense, HttpStatus.OK);
	}

	@GetMapping("getExpenses/{userid}")
	public ResponseEntity<Iterable<Expense>> getExpenses(@PathVariable("userid") Integer userId) {
		Iterable<Expense> list = ferService.getExpenses(userId);
		return new ResponseEntity<Iterable<Expense>>(list, HttpStatus.OK);
	}
	@GetMapping("expenseReport")
	public ResponseEntity<Iterable<Expense>> expenseReport(@RequestParam int userId,@RequestParam String type,@RequestParam String fromDate,@RequestParam String toDate) {
		Iterable<Expense> list = ferService.expenseReport(userId, type, fromDate, toDate);
		return new ResponseEntity<Iterable<Expense>>(list, HttpStatus.OK);
	}
	
	/*
	 * @RequestMapping("login/{userid}") public ResponseEntity<User>
	 * getUser(@PathVariable("userid") Integer userId) { ferService.login(username,
	 * password); return new ResponseEntity<User>HttpStatus.OK);
	 *  }
	 */

}