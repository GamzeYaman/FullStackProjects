package com.patika.atmProject.app.customer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patika.atmProject.app.general.enums.Banks;
import com.patika.atmProject.app.general.enums.Genders;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CUSTOMER_DETAIL")
@Data
public class CustomerDetail {

    @Id
    @SequenceGenerator(name = "CUSTOMER_DETAIL", sequenceName = "CUSTOMER_DETAIL_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "CUSTOMER_DETAIL")
    private Long id;

    @Column(name = "CUSTOMER_TC", length = 11, nullable = false)
    private Long customerTc;

    @Column(name = "CUSTOMER_NAME", length = 155, nullable = false)
    private String customerName;

    @Column(name = "CUSTOMER_SURNAME", length = 155, nullable = false)
    private String customerSurname;

    @Column(name = "CUSTOMER_BIRTHDATE", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date customerBirthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 30, nullable = false)
    private Genders gender;

    @Column(name = "MOBILE_PHONE_NUMBER", length = 11, nullable = false)
    private String mobilePhoneNumber;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "BANK", length = 30, nullable = false)
    private Banks bank;

}
