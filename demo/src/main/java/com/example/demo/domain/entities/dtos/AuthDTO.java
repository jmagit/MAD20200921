package com.example.demo.domain.entities.dtos;

import lombok.Data;

@Data
public class AuthDTO {
	private String access_token, refresh_token, scope, token_type;
	private int expires_in;
}

