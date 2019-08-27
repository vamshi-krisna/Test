package com.concretepage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.concretepage.entity.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

	Iterable<Expense> findByUserId(int userId);

	@Query("SELECT e FROM Expense e WHERE e.userId=:userId AND e.type=:type AND e.date BETWEEN :fromDate AND :toDate")
	List<Expense> fetchExpenses(@Param("userId") int userId, @Param("type") String type,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate);

}
