package com.patika.atmProject.app.customer.service.entityService;

import com.patika.atmProject.app.customer.dao.CustomerAccountDetailDao;
import com.patika.atmProject.app.customer.entity.CustomerAccountDetail;
import com.patika.atmProject.app.general.service.BaseEntityService;
import com.patika.atmProject.app.securityGeneral.service.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class CustomerAccountDetailEntityService extends BaseEntityService<CustomerAccountDetail, CustomerAccountDetailDao> {

    public CustomerAccountDetailEntityService(CustomerAccountDetailDao dao) {
        super(dao);
    }

    public boolean existByIbanNo(String ibanNo){
        return dao.existsByIbanNo(ibanNo);
    }

    public CustomerAccountDetail findAccountDetailByCustomerTc(Long customerTc){
        return dao.findByCustomerTc(customerTc);
    }

    public CustomerAccountDetail findAccountDetailByIbanNo(String ibanNo){
        return dao.findByIbanNo(ibanNo);
    }
}
