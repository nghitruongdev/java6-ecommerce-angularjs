package com.vnco.common.mapper;

import com.vnco.common.model.user.CustomerDTO;
import com.vnco.common.model.user.User;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface UserCustomerMapper {
    
    CustomerDTO toCustomerDTO(User user);
}
