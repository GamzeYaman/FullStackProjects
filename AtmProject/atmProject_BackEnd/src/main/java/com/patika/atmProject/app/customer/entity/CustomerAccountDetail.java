package com.patika.atmProject.app.customer.entity;

import com.patika.atmProject.app.general.enums.AccountStatus;
import lombok.Data;

import javax.persistence.*;
import com.patika.atmProject.app.customer.entity.CustomerDetail;

import java.math.BigDecimal;

@Entity
@Table(name = "CUSTOMER_ACCOUNT_DETAIL")
@Data
public class CustomerAccountDetail {

    @Id
    @SequenceGenerator(name = "CUSTOMER_ACCOUNT_DETAIL", sequenceName = "CUSTOMER_ACCOUNT_DETAIL_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "CUSTOMER_ACCOUNT_DETAIL")
    private Long id;

    @Column(name = "CUSTOMER_TC")
    private Long customerTc;

    @Column(name = "IBAN_NO", length = 26, nullable = false)
    private String ibanNo;

    @Column(name = "AMOUNT", precision = 19, scale = 2, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_STATUS", length = 30, nullable = false)
    private AccountStatus accountStatus;
}
