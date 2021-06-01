package com.natanribeiro.appvendas.domain.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.natanribeiro.appvendas.domain.entity.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Integer>{
	
	public boolean existsByNome(String nome);
	
	@Query(value="SELECT * FROM cliente c WHERE upper(c.nome) LIKE %:nome%", nativeQuery=true)
	public List<Cliente> findByNomeLike(@Param("nome") String nome);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM cliente c WHERE c.nome = :nome", nativeQuery = true)
	public void deleteByName(@Param("nome") String nome);//apenas para estudo
	
	@Query(value="SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos where c.id = :id")
	public Cliente findClientefecthPedidos(@Param("id") int id);
}
