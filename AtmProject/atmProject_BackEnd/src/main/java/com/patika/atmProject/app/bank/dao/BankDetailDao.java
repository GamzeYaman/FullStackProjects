package com.patika.atmProject.app.bank.dao;

import com.patika.atmProject.app.bank.dto.BankCustomersDto;
import com.patika.atmProject.app.bank.entity.BankDetail;
import com.patika.atmProject.app.general.enums.Banks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankDetailDao extends JpaRepository<BankDetail, Long> {
    boolean existsByBankName(Banks bankName);

    @Query(
            value = "select new com.patika.atmProject.app.bank.dto.BankCustomersDto(customerDetail.customerName, customerDetail.customerSurname, customerDetail.gender, customerDetail.mobilePhoneNumber, bankDetail.bankName)" +
                    " from CustomerDetail customerDetail" +
                    " left join BankDetail bankDetail on bankDetail.bankName = customerDetail.bank" +
                    " where bankDetail.bankName = :bankName"
    )
    List<BankCustomersDto> getCustomerInfoByBank(Banks bankName);

}
