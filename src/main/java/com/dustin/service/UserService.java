package com.dustin.service;

import com.dustin.constants.Status;
import com.dustin.constants.UserStatusChangeAction;
import com.dustin.model.entity.SystemUser;
import com.dustin.model.response.PagingResponseModel;
import com.dustin.model.response.UserResponseDTO;
import org.springframework.data.domain.Pageable;

public interface UserService extends GenericService<SystemUser, Long> {
    PagingResponseModel<UserResponseDTO> search(String searchValue, Status status, Pageable pageable);
    UserResponseDTO changeUserStatus(Long userId, UserStatusChangeAction action);

    UserResponseDTO getUserById(Long userId);

    boolean checkUsername(String username);

}
