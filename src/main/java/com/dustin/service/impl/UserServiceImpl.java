package com.dustin.service.impl;

import com.dustin.constants.Status;
import com.dustin.constants.UserStatusChangeAction;
import com.dustin.model.entity.SystemUser;
import com.dustin.model.response.PagingResponseModel;
import com.dustin.model.response.UserResponseDTO;
import com.dustin.model.response.mapper.UserResponseDTOMapper;
import com.dustin.patterns.factory.UserStateFactory;
import com.dustin.patterns.state.user.UserContext;
import com.dustin.patterns.state.user.UserState;
import com.dustin.repository.UserRepository;
import com.dustin.service.JpaSpecification;
import com.dustin.service.UserService;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import static com.dustin.constants.Status.ACTIVE;
import static com.dustin.constants.Status.INACTIVE;

@Service
@AllArgsConstructor
public class UserServiceImpl extends GenericServiceImpl<SystemUser, Long> implements UserService {

    private final JpaSpecification<SystemUser> specification = new JpaSpecification<>();

    private final UserResponseDTOMapper userResponseDTOMapper;

    private final UserRepository userRepository;

    private final UserStateFactory userStateFactory;

    private final UserContext userContext;

    @Override
    public PagingResponseModel<UserResponseDTO> search(String searchValue, Status status, Pageable pageable) {
        Specification<SystemUser> condition = hasUsernameOrEmail(searchValue)
                .and(hasMatchStatus(status));
        Page<SystemUser> userPage = findPaging(condition, pageable);
        Page<UserResponseDTO> userResponsePage = userPage.map(userResponseDTOMapper::toDto);

        return new PagingResponseModel<>(userResponsePage);
    }

    @Override
    public UserResponseDTO changeUserStatus(Long userId, UserStatusChangeAction action) {
        UserState userState = userStateFactory.getUserStateService(action);
        userContext.setUserState(userState);
        userContext.doAction();
        return null;
    }

    @Override
    public UserResponseDTO getUserById(Long userId) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        BeanUtils.copyProperties(userRepository.getUserById(userId), userResponseDTO);
        return userResponseDTO;
    }

    @Override
    public boolean checkUsername(String username) {
        return userRepository.checkUsernameExist(username);
    }

    private Specification<SystemUser> hasUsernameOrEmail(String searchValue) {
        if (searchValue == null) {
            return specification.conjunction();
        }
        return specification.like("username", searchValue).or(specification.like("email", searchValue));
    }

    private Specification<SystemUser> hasMatchStatus(Status status) {
        String statusField = "status";
        if (status == null) {
            return specification.conjunction();
        }
        switch (status) {
            case ACTIVE:  {
                return specification.equals(statusField, ACTIVE);
            }
            case INACTIVE: {
                return specification.equals(statusField, INACTIVE);
            }
        }
        return specification.conjunction();
    }
}
