package com.dustin.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class UserDetails extends User {
    private String email;
    private String jwt;

    public UserDetails(String _email
            , String username
            , String password
            , String _jwt
            , Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = _email;
        this.jwt = _jwt;
    }
}
