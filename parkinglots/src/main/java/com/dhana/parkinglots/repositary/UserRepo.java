package com.dhana.parkinglots.repositary;

import com.dhana.parkinglots.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUsername(String username);

}
