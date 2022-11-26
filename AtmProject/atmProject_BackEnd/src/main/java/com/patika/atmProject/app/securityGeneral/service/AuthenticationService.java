package com.patika.atmProject.app.securityGeneral.service;

import com.patika.atmProject.app.customer.dto.CustomerDetailDto;
import com.patika.atmProject.app.customer.dto.CustomerDetailSaveDto;
import com.patika.atmProject.app.customer.service.CustomerDetailService;
import com.patika.atmProject.app.customer.service.entityService.CustomerDetailEntityService;
import com.patika.atmProject.app.securityGeneral.dto.SecurityLoginRequestDto;
import com.patika.atmProject.app.securityGeneral.enums.EnumJwtConstant;
import com.patika.atmProject.app.securityGeneral.security.JwtTokenGenerator;
import com.patika.atmProject.app.securityGeneral.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CustomerDetailService customerDetailService;
    private final CustomerDetailEntityService customerDetailEntityService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    /* Customer is saved with this method. */
    public CustomerDetailDto register(CustomerDetailSaveDto customerDetailSaveDto) {
        CustomerDetailDto customerDetailDto = customerDetailService.saveCustomerDetail(customerDetailSaveDto);
        return customerDetailDto;
    }

    public String login(SecurityLoginRequestDto securityLoginRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(securityLoginRequestDto.getCustomerTc(), securityLoginRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);
        String bearer = EnumJwtConstant.BEARER.getConstant();
        return bearer + token;
    }


    public Long getCurrentUserId(){
        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        Long jwtUserId = null;
        if(jwtUserDetails != null){
            jwtUserId = jwtUserDetails.getId();
        }
        return jwtUserId;
    }

    public Long getCurrentCustomerTc(){
        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        Long jwtCustomerTc = null;
        if(jwtUserDetails != null){
            jwtCustomerTc = Long.parseLong(jwtUserDetails.getUsername()); //Writes username but it is customer tc. It is like that because jwtUserDetails required String username;
        }
        return jwtCustomerTc;
    }

    private JwtUserDetails getCurrentJwtUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = null;
        if(authentication != null && authentication.getPrincipal() instanceof JwtUserDetails){
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }

}
