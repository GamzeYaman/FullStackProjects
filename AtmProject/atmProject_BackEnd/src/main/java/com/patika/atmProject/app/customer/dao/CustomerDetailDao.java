package com.patika.atmProject.app.customer.dao;

import com.patika.atmProject.app.customer.dto.ShowAccountDetailDto;
import com.patika.atmProject.app.customer.entity.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDetailDao extends JpaRepository<CustomerDetail, Long> {
    boolean existsByCustomerTc(Long customerTc);
    boolean existsByMobilePhoneNumber(String phoneNumber);

    @Query(
            value = "select new com.patika.atmProject.app.customer.dto.ShowAccountDetailDto(customerDetail.customerName, customerDetail.customerSurname, customerDetail.bank, customerAccountDetail.ibanNo, customerAccountDetail.amount)" +
                    " from CustomerDetail customerDetail" +
                    " left join CustomerAccountDetail  customerAccountDetail on customerAccountDetail.customerTc = customerDetail.customerTc" +
                    " where customerDetail.customerTc = :customerTc"
    )
    List<ShowAccountDetailDto> getByCustomerTc(Long customerTc);

    CustomerDetail findByCustomerTc(Long customerTc);
}
