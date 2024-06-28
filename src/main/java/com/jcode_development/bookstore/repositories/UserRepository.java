package com.jcode_development.bookstore.repositories;

import com.jcode_development.bookstore.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("SELECT u FROM User u WHERE u.userName = :userName")
	UserDetails findByUsername(@Value("username") String userName);
	
	
}
