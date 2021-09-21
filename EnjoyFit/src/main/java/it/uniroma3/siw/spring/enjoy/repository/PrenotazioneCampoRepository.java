package it.uniroma3.siw.spring.enjoy.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.enjoy.model.Cliente;
import it.uniroma3.siw.spring.enjoy.model.FasciaOraria;
import it.uniroma3.siw.spring.enjoy.model.PrenotazioneCampo;

public interface PrenotazioneCampoRepository extends CrudRepository<PrenotazioneCampo,Long> {
	
	public List<PrenotazioneCampo> findByCliente(Cliente c);

	public List<PrenotazioneCampo> findByDataAndOrario(LocalDate data, FasciaOraria orario);

}
