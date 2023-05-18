package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.UserAdmin;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.UserAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAdminServiceImpl implements UserAdminService{
    @Autowired
    UserAdminRepository userAdminRepository;

    @Override
    public UserAdmin findById(Long id) throws ElementNotFoundException {
        Optional<UserAdmin> optionalUserAdmin = userAdminRepository.findById(id);
        if(optionalUserAdmin.isEmpty()) throw new ElementNotFoundException("Usuario administrador no existente.");

        return optionalUserAdmin.get();
    }

    @Override
    public List<UserAdmin> findAll() {
        return userAdminRepository.findAll();
    }
}
