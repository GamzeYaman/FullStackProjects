package com.patika.atmProject.app.customer.service;

import com.patika.atmProject.app.customer.converter.CustomerDetailMapperConverter;
import com.patika.atmProject.app.customer.dto.*;
import com.patika.atmProject.app.customer.entity.CustomerAccountDetail;
import com.patika.atmProject.app.customer.entity.CustomerDetail;
import com.patika.atmProject.app.customer.service.entityService.CustomerAccountDetailEntityService;
import com.patika.atmProject.app.customer.service.entityService.CustomerDetailEntityService;
import com.patika.atmProject.app.general.enums.AccountStatus;
import com.patika.atmProject.app.general.exception.exceptionEnums.GeneralErrorMessage;
import com.patika.atmProject.app.general.exception.exceptions.DoesNotExistExceptions;
import com.patika.atmProject.app.general.exception.exceptions.DuplicateExceptions;
import com.patika.atmProject.app.general.exception.exceptions.InvalidVariableExceptions;
import com.patika.atmProject.app.general.exception.exceptions.NotEnoughExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CustomerDetailService {
    public final CustomerDetailEntityService customerDetailEntityService;
    private final CustomerAccountDetailEntityService customerAccountDetailEntityService;
    private final PasswordEncoder passwordEncoder;

    public CustomerDetailDto saveCustomerDetail(CustomerDetailSaveDto customerDetailSaveDto){
        isLengthOfCustomerTcCorrect(customerDetailSaveDto.getCustomerTc());
        isCustomerTcUnique(customerDetailSaveDto.getCustomerTc());
        isCustomerNameOrSurnameContainsDiffCharacterFromLetter(customerDetailSaveDto.getCustomerName(), customerDetailSaveDto.getCustomerSurname());
        isPhoneNumberOnlyContainsNumber(customerDetailSaveDto.getMobilePhoneNumber());
        isCustomerPhoneNumberUnique(customerDetailSaveDto.getMobilePhoneNumber());

        String encodedPassword = passwordEncoder.encode(customerDetailSaveDto.getPassword());

        CustomerDetail customerDetail = CustomerDetailMapperConverter.INSTANCE.convertToCustomerDetailFromCustomerDetailSaveDto(customerDetailSaveDto);
        customerDetail.setPassword(encodedPassword);
        saveCustomerAccountDetail(customerDetail.getCustomerTc());
        customerDetail = customerDetailEntityService.save(customerDetail);
        CustomerDetailDto customerDetailDto =  CustomerDetailMapperConverter.INSTANCE.convertToCustomerDetailDtoFromCustomerDetail(customerDetail);
        return customerDetailDto;
    }

    public CustomerAccountDetailDto saveCustomerAccountDetail(Long customerTc){
        CustomerAccountDetailSaveDto customerAccountDetailSaveDto = new CustomerAccountDetailSaveDto();
        customerAccountDetailSaveDto.setCustomerTc(customerTc);
        customerAccountDetailSaveDto.setAmount(BigDecimal.ZERO);
        customerAccountDetailSaveDto.setAccountStatus(AccountStatus.AKTIF);
        String ibanNo = createIbanNo();
        isIbanNoUnique(ibanNo);
        customerAccountDetailSaveDto.setIbanNo(ibanNo);


        CustomerAccountDetail customerAccountDetail = CustomerDetailMapperConverter.INSTANCE.convertToCustomerAccountDetailFromCustomerAccountDetailSaveDto(customerAccountDetailSaveDto);
        customerAccountDetail = customerAccountDetailEntityService.save(customerAccountDetail);

        CustomerAccountDetailDto customerAccountDetailDto = CustomerDetailMapperConverter.INSTANCE.convertToCustomerAccountDetailDtoFromCustomerAccountDetail(customerAccountDetail);
        return customerAccountDetailDto;
    }

    public CustomerAccountDetail addMoneyToAccount(String enteredIbanNo, BigDecimal newAmount){
        Long customerTc = customerDetailEntityService.getCurrentCustomer();
        isIbanNoLengthCorrect(enteredIbanNo);
        isAccountActive(enteredIbanNo);
        isAmountBiggerThanZero(newAmount);
        CustomerAccountDetail customerAccountDetail = customerAccountDetailEntityService.findAccountDetailByCustomerTc(customerTc);
        String ibanNo = customerAccountDetail.getIbanNo();
        BigDecimal sumOfAmount = newAmount.add(customerAccountDetail.getAmount());

        if(enteredIbanNo.equals(ibanNo)){
            customerAccountDetail.setAmount(sumOfAmount);
            customerAccountDetail = customerAccountDetailEntityService.save(customerAccountDetail);
        }else {
            throw new DoesNotExistExceptions(GeneralErrorMessage.IBAN_NOT_FOUND);
        }

        return customerAccountDetail;
    }

    public CustomerAccountDetail withdrawFromAccount(String enteredIbanNo, BigDecimal amountOfMoney){
        Long customerTc = customerDetailEntityService.getCurrentCustomer();
        isIbanNoLengthCorrect(enteredIbanNo);
        isAccountActive(enteredIbanNo);
        isAmountBiggerThanZero(amountOfMoney);
        CustomerAccountDetail customerAccountDetail = customerAccountDetailEntityService.findAccountDetailByCustomerTc(customerTc);
        String ibanNo = customerAccountDetail.getIbanNo();

        BigDecimal currentAmount = customerAccountDetail.getAmount();
        isCurrentAmountEnoughForWithdraw(currentAmount, amountOfMoney);
        BigDecimal newAmount = currentAmount.subtract(amountOfMoney);

        if(enteredIbanNo.equals(ibanNo)){
            customerAccountDetail.setAmount(newAmount);
            customerAccountDetail = customerAccountDetailEntityService.save(customerAccountDetail);
        }else {
            throw new DoesNotExistExceptions(GeneralErrorMessage.IBAN_NOT_FOUND);
        }
        return customerAccountDetail;
    }

    public CustomerAccountDetail makingCashRemittance(String firstCustomerIbanNo, String secondCustomerIbanNo, BigDecimal money){
        isIbanNoLengthCorrect(firstCustomerIbanNo);
        isAccountActive(firstCustomerIbanNo);
        isIbanNoLengthCorrect(secondCustomerIbanNo);
        isIbanNoExist(secondCustomerIbanNo);
        isAccountActive(secondCustomerIbanNo);
        isAmountBiggerThanZero(money);

        Long customerTc = customerDetailEntityService.getCurrentCustomer();
        CustomerAccountDetail firstCustomerAccountDetail = customerAccountDetailEntityService.findAccountDetailByCustomerTc(customerTc);
        CustomerAccountDetail secondCustomerAccountDetail = customerAccountDetailEntityService.findAccountDetailByIbanNo(secondCustomerIbanNo);

        BigDecimal currentAmount = firstCustomerAccountDetail.getAmount();
        isCurrentAmountEnoughForWithdraw(currentAmount, money);
        BigDecimal newAmountOfFirstCustomer = currentAmount.subtract(money);
        BigDecimal newAmountOfSecondCustomer = money.add(secondCustomerAccountDetail.getAmount());

        if(firstCustomerIbanNo.equals(firstCustomerAccountDetail.getIbanNo())){
            firstCustomerAccountDetail.setAmount(newAmountOfFirstCustomer);
            secondCustomerAccountDetail.setAmount(newAmountOfSecondCustomer);
            secondCustomerAccountDetail = customerAccountDetailEntityService.save(secondCustomerAccountDetail);
            firstCustomerAccountDetail = customerAccountDetailEntityService.save(firstCustomerAccountDetail);
        }else{
            throw new InvalidVariableExceptions(GeneralErrorMessage.INTERNAL_SERVER_ERROR);
        }
        return firstCustomerAccountDetail;
    }

    public List<ShowAccountDetailDto> getCustomerAccountDetailsByCustomerTc(){
        Long customerTc = customerDetailEntityService.getCurrentCustomer();
        List<ShowAccountDetailDto> showAccountDetailDtoList = customerDetailEntityService.getByCustomerTc(customerTc);
        return showAccountDetailDtoList;
    }

    public void changeAccountStatus(Long customertc){
        CustomerAccountDetail customerAccountDetail = customerAccountDetailEntityService.findAccountDetailByCustomerTc(customertc);
        if(customerAccountDetail.getAccountStatus().equals(AccountStatus.PASIF)){
            customerAccountDetail.setAccountStatus(AccountStatus.AKTIF);
        }else{
            customerAccountDetail.setAccountStatus(AccountStatus.PASIF);
        }
    }
    private String createIbanNo(){

        Random random = new Random();
        String ibanNo =  "TR";

        for(int i = 0; i < 24; i++){
            int number = random.nextInt(10) + 0;
            ibanNo += Integer.toString(number);
        }
        return ibanNo;
    }

    ////VALIDATIONS

    //Customer tc must not be less than or more than 11. It's length has to be equal 11!
    private boolean isLengthOfCustomerTcCorrect(Long customerTc){
         if(Long.toString(customerTc).length() == 11){
             return true;
         }else {
             throw new InvalidVariableExceptions(GeneralErrorMessage.TC_LENGTH_ERROR);
         }
    }

    private boolean isCustomerTcUnique(Long customerTc){
        if(customerDetailEntityService.existByCustomerTc(customerTc)){
            throw new DuplicateExceptions(GeneralErrorMessage.TC_ALREADY_USED);
        }else{
            return true;
        }
    }

    private boolean isCustomerPhoneNumberUnique(String phoneNumber){
        if(customerDetailEntityService.existByPhoneNumber(phoneNumber)){
            throw new DuplicateExceptions(GeneralErrorMessage.PHONE_NUMBER_ALREADY_USED);
        }else{
            return true;
        }
    }

    private boolean isCustomerNameOrSurnameContainsDiffCharacterFromLetter(String customerName, String customerSurname){
        if(Pattern.matches("[a-zA-ZğüşöçıIĞÜŞÖÇ]+", customerName)){
            if(Pattern.matches("[a-zA-Z]+", customerSurname)){
                return true;
            }else{
                throw new InvalidVariableExceptions(GeneralErrorMessage.INCLUDE_ONLY_LETTER_ERROR);
            }
        }else{
            throw new InvalidVariableExceptions(GeneralErrorMessage.INCLUDE_ONLY_LETTER_ERROR);
        }
    }

    private boolean isPhoneNumberOnlyContainsNumber(String phoneNumber){
        if(Pattern.matches("[0-9]+", phoneNumber)){
            if(phoneNumber.length() == 11){
                return true;
            }else{
                throw new InvalidVariableExceptions(GeneralErrorMessage.PHONE_NUMBER_LENGTH_ERROR);
            }
        }else{
            throw new InvalidVariableExceptions(GeneralErrorMessage.INCLUDE_ONLY_NUMBER_ERROR);
        }
    }

    private boolean isIbanNoUnique(String ibanNo){
        if(customerAccountDetailEntityService.existByIbanNo(ibanNo)){
            throw new DuplicateExceptions(GeneralErrorMessage.IBAN_UNIQUE_ERROR);
        }else{
            return true;
        }
    }

    private boolean isAmountBiggerThanZero(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) > 0){
            return true;
        }else{
            throw new NotEnoughExceptions(GeneralErrorMessage.NOT_AVAILABLE_AMOUNT);
        }
    }

    private boolean isIbanNoLengthCorrect(String ibanNo){
        if(ibanNo.length() == 26){
            return true;
        }else{
            throw new InvalidVariableExceptions(GeneralErrorMessage.IBAN_LENGTH_ERROR);
        }
    }

    private boolean isCurrentAmountEnoughForWithdraw(BigDecimal currentAmount, BigDecimal money){
        if(currentAmount.compareTo(money) == 1){
            return true;
        }else{
            throw new NotEnoughExceptions(GeneralErrorMessage.NOT_ENOUGH_AMOUNT);
        }
    }

    private boolean isIbanNoExist(String ibanNo){
        if(customerAccountDetailEntityService.existByIbanNo(ibanNo)){
            return true;
        }else{
            throw new DoesNotExistExceptions(GeneralErrorMessage.IBAN_NOT_FOUND);
        }
    }

    private boolean isAccountActive(String ibanNo){
        CustomerAccountDetail customerAccountDetail = customerAccountDetailEntityService.findAccountDetailByIbanNo(ibanNo);
        if(customerAccountDetail.getAccountStatus().equals(AccountStatus.AKTIF)){
            return true;
        }else{
            throw new InvalidVariableExceptions(GeneralErrorMessage.ACCOUNT_STATUS_ERROR);
        }
    }
}
