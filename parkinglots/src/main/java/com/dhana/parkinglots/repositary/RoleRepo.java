package com.dhana.parkinglots.repositary;

import com.dhana.parkinglots.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
