package com.dustin.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleUserRequestDTO {
    
    private String username;

    private String password;

    private String email;
}
