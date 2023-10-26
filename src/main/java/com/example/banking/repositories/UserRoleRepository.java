package com.example.banking.repositories;

import com.example.banking.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
//    @Query(value = "SELECT* FROM public.user_roles WHERE role = :role",nativeQuery = true)
//    List<UserRole> findByRole(String role);

    UserRole findByRole(String role);
}
