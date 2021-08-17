package com.invoicemgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.invoicemgmt.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.username=?1")
	User getByUsername(String username);

}
