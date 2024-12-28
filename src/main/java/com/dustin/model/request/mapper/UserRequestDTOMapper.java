package com.dustin.model.request.mapper;

import com.dustin.model.entity.SystemUser;
import com.dustin.model.request.UserRequestDTO;
import com.dustin.service.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRequestDTOMapper extends EntityMapper<UserRequestDTO, SystemUser> {
}
