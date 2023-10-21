package com.example.banking.repositories;

import com.example.banking.models.User;
import com.example.banking.models.UserAndRole;
import com.example.banking.models.UserAndRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAndRoleRepository extends JpaRepository<UserAndRole, UserAndRoleKey> {
    @Query(value = "SELECT* FROM public.users_and_roles WHERE user_role_id = :userRoleId",nativeQuery = true)
    List<UserAndRole> findByUserRoleId(Long userRoleId);

    @Query(value = "SELECT* FROM public.users_and_roles WHERE user_id = :userId",nativeQuery = true)
    List<UserAndRole> findByUserId(Long userId);
}
