package com.patika.atmProject.app.bank.service;

import com.patika.atmProject.app.bank.converter.BankDetailMapperConverter;
import com.patika.atmProject.app.bank.dto.BankCustomersDto;
import com.patika.atmProject.app.bank.dto.BankDetailDto;
import com.patika.atmProject.app.bank.dto.BankDetailSaveDto;
import com.patika.atmProject.app.bank.entity.BankDetail;
import com.patika.atmProject.app.bank.service.entityService.BankDetailEntityService;
import com.patika.atmProject.app.general.enums.Banks;
import com.patika.atmProject.app.general.exception.exceptionEnums.GeneralErrorMessage;
import com.patika.atmProject.app.general.exception.exceptions.DuplicateExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankDetailService {
    private final BankDetailEntityService bankDetailEntityService;
    private final PasswordEncoder passwordEncoder;
    public BankDetailDto saveBankDetail(BankDetailSaveDto bankDetailSaveDto){
        isBankNameUnique(bankDetailSaveDto.getBankName());

        String encodedPassword = passwordEncoder.encode(bankDetailSaveDto.getBankPassword());
        BankDetail bankDetail = BankDetailMapperConverter.INSTANCE.convertToBankDetailFromBankDetailSaveDto(bankDetailSaveDto);
        bankDetail.setBankPassword(encodedPassword);
        bankDetail = bankDetailEntityService.save(bankDetail);

        BankDetailDto bankDetailDto = BankDetailMapperConverter.INSTANCE.convertToBankDetailDtoFromBAnkDetail(bankDetail);
        return bankDetailDto;
    }

    public List<BankCustomersDto> getBankCustomersByBankName(Banks bankName){
        List<BankCustomersDto> bankCustomersList = bankDetailEntityService.getBankCustomerByBank(bankName);
        return bankCustomersList;
    }
    private boolean isBankNameUnique(Banks bankName){
        if(bankDetailEntityService.existByBankName(bankName)){
            throw new DuplicateExceptions(GeneralErrorMessage.BANK_NAME_ALREADY_USED);
        }else{
            return true;
        }
    }
}
