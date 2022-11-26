package com.patika.atmProject.app.bank.entity;

import com.patika.atmProject.app.general.enums.Banks;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BANK_DETAIL")
@Data
public class BankDetail {

    @Id
    @SequenceGenerator(name = "BANK_DETAIL", sequenceName = "BANK_DETAIL_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "BANK_DETAIL")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "BANK_NAME", length = 30, nullable = false)
    private Banks bankName;

    @Column(name = "BANK_PASSWORD", nullable = false)
    private String bankPassword;
}
