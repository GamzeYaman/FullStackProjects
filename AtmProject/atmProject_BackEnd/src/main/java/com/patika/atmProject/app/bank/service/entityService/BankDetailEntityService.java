package com.patika.atmProject.app.bank.service.entityService;

import com.patika.atmProject.app.bank.dao.BankDetailDao;
import com.patika.atmProject.app.bank.dto.BankCustomersDto;
import com.patika.atmProject.app.bank.entity.BankDetail;
import com.patika.atmProject.app.general.enums.Banks;
import com.patika.atmProject.app.general.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankDetailEntityService extends BaseEntityService<BankDetail, BankDetailDao> {

    public BankDetailEntityService(BankDetailDao dao) {
        super(dao);
    }

    public boolean existByBankName(Banks bankName){
        return dao.existsByBankName(bankName);
    }

    public List<BankCustomersDto> getBankCustomerByBank(Banks bankName){
        return dao.getCustomerInfoByBank(bankName);
    }
}
