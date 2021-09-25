package it.uniroma3.siw.spring.enjoy.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.enjoy.model.Cliente;
import it.uniroma3.siw.spring.enjoy.model.FasciaOraria;
import it.uniroma3.siw.spring.enjoy.model.PrenotazioneCampo;
import it.uniroma3.siw.spring.enjoy.repository.PrenotazioneCampoRepository;

@Service
public class PrenotazioneCampoService {

	@Autowired
	private PrenotazioneCampoRepository prenotazioneRepository;
	

	
	@Transactional
	public PrenotazioneCampo inserisci(PrenotazioneCampo prenotazione) {
		return this.prenotazioneRepository.save(prenotazione);
	}

	@Transactional
	public PrenotazioneCampo prenotazionePerId(Long id) {
		Optional<PrenotazioneCampo> optional = prenotazioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public List<PrenotazioneCampo> getByCliente(Cliente cliente) {
		return (List<PrenotazioneCampo>)this.prenotazioneRepository.findByCliente(cliente);
	}
	
	@Transactional
	public List<PrenotazioneCampo> tutti() {
		return (List<PrenotazioneCampo>)this.prenotazioneRepository.findAll();
	}
	
	@Transactional
	public List<PrenotazioneCampo> getByDataAndOrario(LocalDate data, FasciaOraria orario) {
		return (List<PrenotazioneCampo>)this.prenotazioneRepository.findByDataAndOrario(data, orario);
	}

	@Transactional
	public void cancella(Long id) {
		this.prenotazioneRepository.deleteById(id);		
	}

	@Transactional
	public PrenotazioneCampo getById(Long id) {
		Optional<PrenotazioneCampo> optional = this.prenotazioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

}
