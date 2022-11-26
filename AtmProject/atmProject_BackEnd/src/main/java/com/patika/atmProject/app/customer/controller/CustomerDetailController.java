package com.patika.atmProject.app.customer.controller;

import com.patika.atmProject.app.customer.dto.ShowAccountDetailDto;
import com.patika.atmProject.app.customer.entity.CustomerAccountDetail;
import com.patika.atmProject.app.customer.service.CustomerDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerDetailController {
    private final CustomerDetailService customerDetailService;

    @GetMapping("/account-details")
    public ResponseEntity getCustomerAccountDetails(){
        List<ShowAccountDetailDto> showAccountDetailDtoList = customerDetailService.getCustomerAccountDetailsByCustomerTc();
        return ResponseEntity.ok(showAccountDetailDtoList);
    }

    @PatchMapping("/add-money")
    public ResponseEntity addMoneyToAccount(@RequestParam String ibanNo, @RequestParam BigDecimal amount){
        CustomerAccountDetail customerAccountDetail = customerDetailService.addMoneyToAccount(ibanNo, amount);
        return ResponseEntity.ok(customerAccountDetail);
    }

    @PatchMapping("/withdraw")
    public ResponseEntity withdrawFromAccount(@RequestParam String ibanNo,  @RequestParam BigDecimal money){
        CustomerAccountDetail customerAccountDetail = customerDetailService.withdrawFromAccount(ibanNo, money);
        return ResponseEntity.ok(customerAccountDetail);
    }

    @PatchMapping("/cash-remittance")
    public ResponseEntity makingCashRemittance(@RequestParam String firstCustomerIbanNo, @RequestParam String secondCustomerIbanNo, @RequestParam BigDecimal money){
        CustomerAccountDetail customerAccountDetail = customerDetailService.makingCashRemittance(firstCustomerIbanNo, secondCustomerIbanNo, money);
        return ResponseEntity.ok(customerAccountDetail);
    }
}
