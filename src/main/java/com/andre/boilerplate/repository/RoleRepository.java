package com.andre.boilerplate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andre.boilerplate.models.Roles;


public interface RoleRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByName(String name);
}
