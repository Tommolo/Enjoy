package it.uniroma3.siw.spring.enjoy.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.enjoy.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente,Long>
{

}
