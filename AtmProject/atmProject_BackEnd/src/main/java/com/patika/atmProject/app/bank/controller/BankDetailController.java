package com.patika.atmProject.app.bank.controller;

import com.patika.atmProject.app.bank.dto.BankCustomersDto;
import com.patika.atmProject.app.bank.dto.BankDetailDto;
import com.patika.atmProject.app.bank.dto.BankDetailSaveDto;
import com.patika.atmProject.app.bank.service.BankDetailService;
import com.patika.atmProject.app.general.enums.Banks;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class BankDetailController {

    private final BankDetailService bankDetailService;

    @PostMapping("/save-bank")
    public ResponseEntity saveBankDetail(@RequestBody BankDetailSaveDto bankDetailSaveDto){
        BankDetailDto bankDetailDto = bankDetailService.saveBankDetail(bankDetailSaveDto);
        return ResponseEntity.ok(bankDetailDto);
    }

    @GetMapping("/{bankName}")
    public ResponseEntity getBankCustomersByBankName(@PathVariable Banks bankName){
        List<BankCustomersDto> bankCustomersList = bankDetailService.getBankCustomersByBankName(bankName);
        return ResponseEntity.ok(bankCustomersList);
    }
}
