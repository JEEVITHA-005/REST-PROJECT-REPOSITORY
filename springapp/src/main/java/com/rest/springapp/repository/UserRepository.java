package com.rest.springapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rest.springapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // ✅ Corrected: Find all users with pagination and sorting
    Page<User> findAll(Pageable pageable);

    // ✅ Find user by username (case insensitive)
    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)")
    User findByUsernameIgnoreCase(String username);
    

    // ✅ Insert a new user (Handled via native query)
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (username, email, password) VALUES (:username, :email, :password)", nativeQuery = true)
    int insertUser(String username, String email, String password);

    // ✅ Update user details using JPQL
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.username = :username, u.email = :email, u.password = :password WHERE u.id = :id")
    int updateUser(Long id, String username, String email, String password);

    // ✅ Delete a user by ID using JPQL
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = :id")
    int deleteUserById(Long id);
}
