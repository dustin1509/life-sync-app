package com.dustin.service;

import com.dustin.model.entity.SystemUser;
import com.dustin.model.request.JwtRequestDTO;
import com.dustin.model.request.SimpleUserRequestDTO;
import com.dustin.model.response.JwtResponseDTO;

public interface AuthenticationService extends GenericService<SystemUser, Long> {

    Long signUp(SimpleUserRequestDTO userRequestDTO);

    JwtResponseDTO signIn(JwtRequestDTO jwtRequestDTO);

}
