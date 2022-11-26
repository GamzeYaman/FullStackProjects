package com.patika.atmProject.app.customer.converter;

import com.patika.atmProject.app.customer.dto.CustomerAccountDetailDto;
import com.patika.atmProject.app.customer.dto.CustomerAccountDetailSaveDto;
import com.patika.atmProject.app.customer.dto.CustomerDetailDto;
import com.patika.atmProject.app.customer.dto.CustomerDetailSaveDto;
import com.patika.atmProject.app.customer.entity.CustomerAccountDetail;
import com.patika.atmProject.app.customer.entity.CustomerDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerDetailMapperConverter {

    CustomerDetailMapperConverter INSTANCE = Mappers.getMapper(CustomerDetailMapperConverter.class);

    CustomerDetail convertToCustomerDetailFromCustomerDetailSaveDto(CustomerDetailSaveDto customerDetailSaveDto);
    CustomerDetailDto convertToCustomerDetailDtoFromCustomerDetail(CustomerDetail customerDetail);

    //CustomerAccountDetail Converters
    CustomerAccountDetail convertToCustomerAccountDetailFromCustomerAccountDetailSaveDto(CustomerAccountDetailSaveDto customerAccountDetailSaveDto);
    CustomerAccountDetailDto convertToCustomerAccountDetailDtoFromCustomerAccountDetail(CustomerAccountDetail customerAccountDetail);
}
