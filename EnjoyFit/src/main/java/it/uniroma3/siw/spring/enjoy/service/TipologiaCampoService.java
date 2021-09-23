package it.uniroma3.siw.spring.enjoy.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.enjoy.model.TipologiaCampo;
import it.uniroma3.siw.spring.enjoy.repository.TipologiaCampoRepository;




@Service
public class TipologiaCampoService {
	
	@Autowired
	private TipologiaCampoRepository tipologiaRepository;
	
	@Transactional
	public TipologiaCampo inserisci(TipologiaCampo col) {
		return tipologiaRepository.save(col);
	}

	@Transactional
	public List<TipologiaCampo> tutti() {
		return (List<TipologiaCampo>) tipologiaRepository.findAll();
	}
	
	@Transactional
	public List<TipologiaCampo> tuttiOrdinati() {
		return tipologiaRepository.findByOrderByNomeAsc();
	}

	@Transactional
	public TipologiaCampo tipoCampoPerId(Long id) {
		Optional<TipologiaCampo> optional = tipologiaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public boolean alreadyExists(TipologiaCampo tip) {
		List<TipologiaCampo> tipologie = this.tipologiaRepository.findByNome(tip.getNome());
		if (tipologie.size() > 0) {
			return true;
		}
		return false;
	}
	
	
	@Transactional
	public void cancella(Long id) {
		this.tipologiaRepository.deleteById(id);
	}
	
	

}
