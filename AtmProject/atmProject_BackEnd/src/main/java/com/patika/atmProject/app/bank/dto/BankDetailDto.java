package com.patika.atmProject.app.bank.dto;

import com.patika.atmProject.app.general.enums.Banks;
import lombok.Data;

@Data
public class BankDetailDto {

    private Long id;
    private Banks bankName;
    private String bankPassword;
}
