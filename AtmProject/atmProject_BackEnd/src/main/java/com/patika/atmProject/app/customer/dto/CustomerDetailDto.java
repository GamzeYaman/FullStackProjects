package com.patika.atmProject.app.customer.dto;

import com.patika.atmProject.app.general.enums.Banks;
import com.patika.atmProject.app.general.enums.Genders;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerDetailDto {

    private Long id;
    private Long customerTc;
    private String customerName;
    private String customerSurname;
    private Date customerBirthdate;
    private Genders gender;
    private String mobilePhoneNumber;
    private String password;
    private Banks bank;
}
