package com.patika.atmProject.app.securityGeneral.security;

import com.patika.atmProject.app.customer.entity.CustomerDetail;
import com.patika.atmProject.app.customer.service.entityService.CustomerDetailEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private  final CustomerDetailEntityService customerDetailEntityService;

    @Override
    public UserDetails loadUserByUsername(String customerTc) throws UsernameNotFoundException {
       CustomerDetail customerDetail = customerDetailEntityService.findByCustomerTc(Long.parseLong(customerTc));
       return JwtUserDetails.create(customerDetail);
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        CustomerDetail customerDetail = customerDetailEntityService.getIdWithControl(id);
        return JwtUserDetails.create(customerDetail);
    }

}
