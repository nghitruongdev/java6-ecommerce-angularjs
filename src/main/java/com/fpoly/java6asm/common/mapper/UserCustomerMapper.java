package com.fpoly.java6asm.common.mapper;

import com.fpoly.java6asm.rest.entity.user.User;
import com.fpoly.java6asm.rest.entity.user.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface UserCustomerMapper {
    
    CustomerDTO toCustomerDTO(User user);
}
