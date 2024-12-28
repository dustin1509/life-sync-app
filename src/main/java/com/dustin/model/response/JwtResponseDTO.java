package com.dustin.model.response;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
public class JwtResponseDTO implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private String accessToken;
	private String refreshToken;
	private String tokenType;
	private int expiresIn;

	public JwtResponseDTO(String accessToken) {
		this.accessToken = accessToken;
	}
}
