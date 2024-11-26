package com.sbear.firstapp.repository;

import com.sbear.firstapp.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Roles getRolesByRoleName(String roleName);
}
