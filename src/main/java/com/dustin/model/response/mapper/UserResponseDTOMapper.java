package com.dustin.model.response.mapper;

import com.dustin.model.entity.SystemUser;
import com.dustin.model.response.UserResponseDTO;
import com.dustin.service.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserResponseDTOMapper extends EntityMapper<UserResponseDTO, SystemUser> {
    @Override
    @Mapping(target = "password", ignore = true)
    UserResponseDTO toDto(SystemUser entity);
}
