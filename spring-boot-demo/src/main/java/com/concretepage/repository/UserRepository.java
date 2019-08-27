package com.concretepage.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.concretepage.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByUsernameAndPassword(String username, String password);

}
