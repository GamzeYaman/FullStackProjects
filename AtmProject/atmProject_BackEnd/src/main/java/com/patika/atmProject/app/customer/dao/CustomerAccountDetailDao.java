package com.patika.atmProject.app.customer.dao;

import com.patika.atmProject.app.customer.entity.CustomerAccountDetail;
import com.patika.atmProject.app.general.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAccountDetailDao extends JpaRepository<CustomerAccountDetail, Long> {
    boolean existsByIbanNo(String ibanNo);
    CustomerAccountDetail findByCustomerTc(Long customerTc);
    CustomerAccountDetail findByIbanNo(String ibanNo);
}
