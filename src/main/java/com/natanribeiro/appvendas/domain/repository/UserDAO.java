package com.natanribeiro.appvendas.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.natanribeiro.appvendas.domain.entity.MyUser;

public interface UserDAO extends JpaRepository<MyUser, Integer>{

	public Optional<MyUser> findUserByUsername(String username);
}
