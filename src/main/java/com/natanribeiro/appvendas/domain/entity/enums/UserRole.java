package com.natanribeiro.appvendas.domain.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority{
	ROLE_USER,
	ROLE_ADMIN;

	@Override
	public String getAuthority() {
		return this.name();
	}
}
