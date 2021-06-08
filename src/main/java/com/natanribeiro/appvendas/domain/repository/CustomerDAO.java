package com.natanribeiro.appvendas.domain.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.natanribeiro.appvendas.domain.entity.Customer;

public interface CustomerDAO extends JpaRepository<Customer, Integer>{
	
	public boolean existsByName(String nome);
	
	@Query(value="SELECT * FROM cliente c WHERE upper(c.nome) LIKE %:nome%", nativeQuery=true)
	public List<Customer> findByNameLike(@Param("nome") String nome);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM cliente c WHERE c.nome = :nome", nativeQuery = true)
	public void deleteByName(@Param("nome") String nome);//apenas para estudo
	
	public void deleteById(Integer id);
	
	@Query(value="SELECT c FROM Customer c LEFT JOIN FETCH c.orders where c.id = :id")
	public Customer findCustomerfecthPedidos(@Param("id") int id);
}
