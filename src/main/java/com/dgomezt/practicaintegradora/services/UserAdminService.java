package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.UserAdmin;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

import java.util.List;

public interface UserAdminService {
    UserAdmin findById(Long id) throws ElementNotFoundException;
    List<UserAdmin> findAll();
}
