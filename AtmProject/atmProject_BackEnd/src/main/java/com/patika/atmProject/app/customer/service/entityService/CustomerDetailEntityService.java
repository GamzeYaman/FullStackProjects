package com.patika.atmProject.app.customer.service.entityService;

import com.patika.atmProject.app.customer.dao.CustomerDetailDao;
import com.patika.atmProject.app.customer.dto.ShowAccountDetailDto;
import com.patika.atmProject.app.customer.entity.CustomerDetail;
import com.patika.atmProject.app.general.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDetailEntityService extends BaseEntityService<CustomerDetail, CustomerDetailDao> {


    public CustomerDetailEntityService(CustomerDetailDao dao) {
        super(dao);
    }

    public boolean existByCustomerTc(Long customerTc){
        return dao.existsByCustomerTc(customerTc);
    }

    public boolean existByPhoneNumber(String phoneNumber){
        return dao.existsByMobilePhoneNumber(phoneNumber);
    }

    public List<ShowAccountDetailDto> getByCustomerTc(Long customerTc){
        return dao.getByCustomerTc(customerTc);
    }

    public CustomerDetail findByCustomerTc(Long customerTc){
        return dao.findByCustomerTc(customerTc);
    }
}
