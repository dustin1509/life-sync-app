package com.dustin.controller;

import com.dustin.config.JwtTokenUtil;
import com.dustin.model.request.JwtRequestDTO;
import com.dustin.model.request.SimpleUserRequestDTO;
import com.dustin.model.request.UserRequestDTO;
import com.dustin.model.response.JwtResponseDTO;
import com.dustin.model.response.UserResponseDTO;
import com.dustin.service.AuthenticationService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService authenticationService;
	private final JwtTokenUtil jwtTokenUtil;

	@PostMapping("sign-up")
	public ResponseEntity<Long> signUp(@RequestBody SimpleUserRequestDTO userRequestDTO) {
		return ResponseEntity.ok(authenticationService.signUp(userRequestDTO));
	}

	@PostMapping("sign-in")
	public ResponseEntity<JwtResponseDTO> signIn(@RequestBody JwtRequestDTO jwtRequestDTO) {
		return ResponseEntity.ok(authenticationService.signIn(jwtRequestDTO));
	}

	@RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
	public ResponseEntity<?> refreshToken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		Claims claims = (Claims) request.getAttribute("claims");

		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
		String token = jwtTokenUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
		return ResponseEntity.ok(new JwtResponseDTO(token));
	}

	public Map<String, Object> getMapFromIoJsonwebtokenClaims(Claims claims) {
		return new HashMap<>(claims);
	}

}
