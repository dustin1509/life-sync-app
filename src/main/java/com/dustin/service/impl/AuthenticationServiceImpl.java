package com.dustin.service.impl;

import com.dustin.config.JwtConfig;
import com.dustin.config.JwtTokenUtil;
import com.dustin.constants.Status;
import com.dustin.model.entity.SystemUser;
import com.dustin.model.request.JwtRequestDTO;
import com.dustin.model.request.SimpleUserRequestDTO;
import com.dustin.model.response.JwtResponseDTO;
import com.dustin.model.response.UserResponseDTO;
import com.dustin.repository.UserRepository;
import com.dustin.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl extends GenericServiceImpl<SystemUser, Long> implements AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;
    private final JwtConfig jwtConfig;
    private final JwtUserDetailsServiceImpl userDetailsService;
    private final Environment env;

    @Override
    public Long signUp(SimpleUserRequestDTO userRequestDTO) {
        SystemUser user = new SystemUser();
        user.setEmail(userRequestDTO.getEmail());
        user.setUsername(userRequestDTO.getUsername());
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setRole("admin");
        user.setStatus(Status.ACTIVE);
        return userRepository.save(user).getId();
    }

    @Override
    public JwtResponseDTO signIn(JwtRequestDTO requestDTO) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(((UserDetails) authentication.getPrincipal()).getUsername());

        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
        jwtResponseDTO.setAccessToken(jwtTokenUtil.generateToken(new org.springframework.security.core.userdetails.User(
                userDetails.getUsername(), userDetails.getPassword(), new ArrayList<>())));
        jwtResponseDTO.setExpiresIn(jwtConfig.getJwtExpirationInMs());
        return jwtResponseDTO;
    }
}
