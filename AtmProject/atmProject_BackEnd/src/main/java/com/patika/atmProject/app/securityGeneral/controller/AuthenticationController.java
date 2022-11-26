package com.patika.atmProject.app.securityGeneral.controller;

import com.patika.atmProject.app.customer.dto.CustomerDetailDto;
import com.patika.atmProject.app.customer.dto.CustomerDetailSaveDto;
import com.patika.atmProject.app.securityGeneral.dto.SecurityLoginRequestDto;
import com.patika.atmProject.app.securityGeneral.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CustomerDetailSaveDto customerDetailSaveDto){
        CustomerDetailDto customerDetailDto = authenticationService.register(customerDetailSaveDto);
        return ResponseEntity.ok(customerDetailDto);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody SecurityLoginRequestDto securityLoginRequestDto){
       String token = authenticationService.login(securityLoginRequestDto);
       return  ResponseEntity.ok(token);
    }
}
