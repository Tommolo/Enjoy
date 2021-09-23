package it.uniroma3.siw.spring.enjoy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.enjoy.model.TipologiaCampo;




public interface TipologiaCampoRepository extends CrudRepository<TipologiaCampo, Long> {
	
	public List<TipologiaCampo> findByNome(String nome);
	
	//riporta le tipologie di campo ordinate per ordine alfabetico
	public List<TipologiaCampo> findByOrderByNomeAsc();

}
