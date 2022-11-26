package com.patika.atmProject.app.bank.converter;

import com.patika.atmProject.app.bank.dto.BankDetailDto;
import com.patika.atmProject.app.bank.dto.BankDetailSaveDto;
import com.patika.atmProject.app.bank.entity.BankDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankDetailMapperConverter {

    BankDetailMapperConverter INSTANCE = Mappers.getMapper(BankDetailMapperConverter.class);

    BankDetail convertToBankDetailFromBankDetailSaveDto(BankDetailSaveDto bankDetailSaveDto);
    BankDetailDto convertToBankDetailDtoFromBAnkDetail(BankDetail bankDetail);
}
