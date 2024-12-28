package com.dustin.model.request.mapper;

import com.dustin.model.entity.SystemUser;
import com.dustin.model.request.UserRequestDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-17T15:00:11+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class UserRequestDTOMapperImpl implements UserRequestDTOMapper {

    @Override
    public SystemUser toEntity(UserRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SystemUser systemUser = new SystemUser();

        systemUser.setUsername( dto.getUsername() );
        systemUser.setPassword( dto.getPassword() );
        systemUser.setEmail( dto.getEmail() );
        systemUser.setFirstName( dto.getFirstName() );
        systemUser.setLastName( dto.getLastName() );
        systemUser.setPhone( dto.getPhone() );
        systemUser.setAddress( dto.getAddress() );
        systemUser.setGender( dto.getGender() );

        return systemUser;
    }

    @Override
    public UserRequestDTO toDto(SystemUser entity) {
        if ( entity == null ) {
            return null;
        }

        UserRequestDTO userRequestDTO = new UserRequestDTO();

        userRequestDTO.setUsername( entity.getUsername() );
        userRequestDTO.setPassword( entity.getPassword() );
        userRequestDTO.setEmail( entity.getEmail() );
        userRequestDTO.setFirstName( entity.getFirstName() );
        userRequestDTO.setLastName( entity.getLastName() );
        userRequestDTO.setPhone( entity.getPhone() );
        userRequestDTO.setAddress( entity.getAddress() );
        userRequestDTO.setGender( entity.getGender() );

        return userRequestDTO;
    }

    @Override
    public List<SystemUser> toEntityList(List<UserRequestDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SystemUser> list = new ArrayList<SystemUser>( dtoList.size() );
        for ( UserRequestDTO userRequestDTO : dtoList ) {
            list.add( toEntity( userRequestDTO ) );
        }

        return list;
    }

    @Override
    public List<UserRequestDTO> toDtoList(List<SystemUser> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserRequestDTO> list = new ArrayList<UserRequestDTO>( entityList.size() );
        for ( SystemUser systemUser : entityList ) {
            list.add( toDto( systemUser ) );
        }

        return list;
    }
}
