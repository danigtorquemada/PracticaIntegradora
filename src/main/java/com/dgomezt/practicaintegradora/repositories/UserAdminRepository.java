package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAdminRepository extends JpaRepository<UserAdmin, Long> {
}