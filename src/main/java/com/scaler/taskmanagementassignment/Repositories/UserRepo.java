package com.scaler.taskmanagementassignment.Repositories;

import com.scaler.taskmanagementassignment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);

}
