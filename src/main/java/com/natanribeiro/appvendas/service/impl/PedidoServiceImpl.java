package com.natanribeiro.appvendas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natanribeiro.appvendas.domain.repository.OrderDAO;
import com.natanribeiro.appvendas.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	@Autowired
	OrderDAO dao;
}
