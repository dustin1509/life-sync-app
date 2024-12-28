package com.dustin.model.response.mapper;

import com.dustin.constants.Status;
import com.dustin.model.entity.SystemUser;
import com.dustin.model.response.UserResponseDTO;
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
public class UserResponseDTOMapperImpl implements UserResponseDTOMapper {

    @Override
    public SystemUser toEntity(UserResponseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SystemUser systemUser = new SystemUser();

        systemUser.setCreatedBy( dto.getCreatedBy() );
        systemUser.setCreatedOn( dto.getCreatedOn() );
        systemUser.setModifiedBy( dto.getModifiedBy() );
        systemUser.setModifiedOn( dto.getModifiedOn() );
        systemUser.setId( dto.getId() );
        systemUser.setUsername( dto.getUsername() );
        systemUser.setPassword( dto.getPassword() );
        systemUser.setEmail( dto.getEmail() );
        systemUser.setFirstName( dto.getFirstName() );
        systemUser.setLastName( dto.getLastName() );
        systemUser.setPhone( dto.getPhone() );
        systemUser.setAddress( dto.getAddress() );
        systemUser.setGender( dto.getGender() );
        if ( dto.getStatus() != null ) {
            systemUser.setStatus( Enum.valueOf( Status.class, dto.getStatus() ) );
        }

        return systemUser;
    }

    @Override
    public List<SystemUser> toEntityList(List<UserResponseDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SystemUser> list = new ArrayList<SystemUser>( dtoList.size() );
        for ( UserResponseDTO userResponseDTO : dtoList ) {
            list.add( toEntity( userResponseDTO ) );
        }

        return list;
    }

    @Override
    public List<UserResponseDTO> toDtoList(List<SystemUser> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserResponseDTO> list = new ArrayList<UserResponseDTO>( entityList.size() );
        for ( SystemUser systemUser : entityList ) {
            list.add( toDto( systemUser ) );
        }

        return list;
    }

    @Override
    public UserResponseDTO toDto(SystemUser entity) {
        if ( entity == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setCreatedBy( entity.getCreatedBy() );
        userResponseDTO.setCreatedOn( entity.getCreatedOn() );
        userResponseDTO.setModifiedBy( entity.getModifiedBy() );
        userResponseDTO.setModifiedOn( entity.getModifiedOn() );
        userResponseDTO.setId( entity.getId() );
        userResponseDTO.setUsername( entity.getUsername() );
        userResponseDTO.setEmail( entity.getEmail() );
        userResponseDTO.setFirstName( entity.getFirstName() );
        userResponseDTO.setLastName( entity.getLastName() );
        userResponseDTO.setPhone( entity.getPhone() );
        userResponseDTO.setAddress( entity.getAddress() );
        userResponseDTO.setGender( entity.getGender() );
        if ( entity.getStatus() != null ) {
            userResponseDTO.setStatus( entity.getStatus().name() );
        }

        return userResponseDTO;
    }
}
