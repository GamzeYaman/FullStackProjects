package com.patika.atmProject.app.general.service;

import com.patika.atmProject.app.securityGeneral.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E, D extends JpaRepository> {

    public final D dao;
    private AuthenticationService authenticationService;

    //Circular Dependency
    @Autowired
    public void setAuthenticationService(@Lazy AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    public E save (E entity){
        return (E) dao.save(entity);
    }

    public Optional<E> findById(Long id){
        return dao.findById(id);
    }
    public E getIdWithControl(Long id){
        Optional<E> entityOptional = findById(id);

        E entity;
        if(entityOptional.isPresent()){
            entity = entityOptional.get();
        }else{
            throw new RuntimeException("Id not found!");
        }
        return entity;
    }

    public Long getCurrentCustomer(){
        Long currentCustomerTc = authenticationService.getCurrentCustomerTc();
        return currentCustomerTc;
    }

}
