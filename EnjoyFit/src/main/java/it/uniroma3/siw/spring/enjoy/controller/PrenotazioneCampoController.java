package it.uniroma3.siw.spring.enjoy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.enjoy.controller.validator.PrenotazioneCampoValidator;
import it.uniroma3.siw.spring.enjoy.model.Cliente;
import it.uniroma3.siw.spring.enjoy.model.Credentials;
import it.uniroma3.siw.spring.enjoy.model.PrenotazioneCampo;
import it.uniroma3.siw.spring.enjoy.service.CredentialsService;
import it.uniroma3.siw.spring.enjoy.service.PrenotazioneCampoService;
import it.uniroma3.siw.spring.enjoy.service.TipologiaCampoService;

@Controller
public class PrenotazioneCampoController {
	
	@Autowired
	private PrenotazioneCampoService prenotazioneService;
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private PrenotazioneCampoValidator prenotazioneValidator;	
	@Autowired
	private TipologiaCampoService tipoService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Cliente getCliente() {
		//retrieve current user
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				String username;

				if (principal instanceof UserDetails) {
					username = ((UserDetails)principal).getUsername();
				} else {
					username = principal.toString();
				}
						
				Credentials c = this.credentialsService.getCredentials(username);
				Cliente cliente = c.getUser();
				return cliente;
	}
	
	/**
	 * l'utente vuole effettuare una nuova prenotazione, viene creato il nuovo
	 * oggetto "vuoto" e restituito il form da compilare
	 */
	@RequestMapping(value="/prenota", method=RequestMethod.GET)
	public String nuovaPrenotazione(Model model) {
		
		PrenotazioneCampo prenotazione = new PrenotazioneCampo();
		logger.debug("HAI CREATO la PRENOTAZIONE NUMERO : ", prenotazione.getId());
		model.addAttribute("prenotazione",prenotazione);
		model.addAttribute("tipoCampi",this.tipoService.tuttiOrdinati());
		return "prenotazione/prenotazioneForm";
		
	}
	
	@RequestMapping(value="/prenota", method=RequestMethod.POST)
	public String nuovaPrenotazionePost(@ModelAttribute("prenotazione") PrenotazioneCampo prenotazione, Model model,
			BindingResult bindingResult) {

		Cliente cliente = getCliente();
		
		this.prenotazioneValidator.validate(prenotazione, bindingResult);
		if (!bindingResult.hasErrors()) {
			prenotazione.setCliente(cliente);
			this.prenotazioneService.inserisci(prenotazione);
			logger.debug("Prenotazion salvata con id: " + prenotazione.getId());
			logger.debug("Prenotazioni salvate in fascia: " + prenotazioneService.getByDataAndOrario(prenotazione.getData(), prenotazione.getOrario()).size());
			model.addAttribute("prenotazione", prenotazione);
			model.addAttribute("tipoCampi",this.tipoService.tuttiOrdinati());
			return ("prenotazione/prenotazione") ;
		}
		return "prenotazione/prenotazioneForm";
	}
	
	/***
	 * l'utente vuole visualizzare i dati di una determinata prenotazione,
	 * anche passata
	 */
	@RequestMapping(value="/prenotazione/{id}", method=RequestMethod.GET) 
	public String getPrenotazione(Model model, @PathVariable("id") Long id) {
		PrenotazioneCampo p = this.prenotazioneService.prenotazionePerId(id);
		if (!this.getCliente().equals(p.getCliente())) {
			return "error";
		}
		model.addAttribute("prenotazione", p);
		model.addAttribute("tipoCampi",this.tipoService.tuttiOrdinati());
		return "prenotazione/prenotazione";
	}
	
	
	
	
	/*****************************************************
	 * ****************ADMIN*******************************
	 ***********************************************************/
	
	/***
	 * l'admin vuole visualizzare i dati di una determinata prenotazione,
	 * anche passata
	 */
	@RequestMapping(value="/prenotazioneAdmin/{id}", method=RequestMethod.GET) 
	public String getPrenotazioneAdmin(Model model, @PathVariable("id") Long id) {
		PrenotazioneCampo p = this.prenotazioneService.prenotazionePerId(id);
		model.addAttribute("prenotazione", p);
		model.addAttribute("tipoCampi",this.tipoService.tuttiOrdinati());
		return "prenotazione/prenotazione";
	}
	
	/**
	 * l'admin visualizza tutte le prenotazioni
	 */
	@RequestMapping(value="/admin/prenotazioni", method=RequestMethod.GET) 
	public String getTuttePrenotazioni(Model model) {
		model.addAttribute("prenotazioni", this.prenotazioneService.tutti());
		return "prenotazione/prenotazioniAdmin";
	}
	/**
	 * l'utente visualizza tutte le sue prenotazioni
	 */
	@RequestMapping(value="/prenotazioni", method=RequestMethod.GET) 
	public String getPrenotazioni(Model model) {
		
		Cliente cliente = getCliente();
		
		//popola pagina
		model.addAttribute("prenotazioni", this.prenotazioneService.getByCliente(cliente));
		return "prenotazione/prenotazioni";
	}
	
	
	
	/**
	 * l'utente vuole eliminare una prenotazione
	 */
	@RequestMapping(value="/elimina/prenotazione/{id}", method=RequestMethod.GET) 
	public String deletePrenotazione(@PathVariable("id") Long id, Model model) {
	    Cliente cliente = getCliente();
	    PrenotazioneCampo p = this.prenotazioneService.getById(id);
	    if (!cliente.equals(p.getCliente()) || !p.isCorrente()) {
	    	return "error";
	    }
	    this.prenotazioneService.cancella(id);
	    model.addAttribute("prenotazioni", this.prenotazioneService.getByCliente(cliente));
	    return "prenotazione/prenotazioni";
	   }
	
	/**
	 * l'utente vuole modificare una prenotazione
	 */
	@RequestMapping(value="/modifica/prenotazione/{id}", method=RequestMethod.GET) 
	public String modificaPrenotazione(@PathVariable("id") Long id, Model model) {
		Cliente cliente = getCliente();
	    PrenotazioneCampo p = this.prenotazioneService.getById(id);
	    if (!cliente.equals(p.getCliente()) || !p.isCorrente()) {
	    	return "error";
	    }
		model.addAttribute("prenotazione", p);
		model.addAttribute("tipoCampi",this.tipoService.tuttiOrdinati());
		return "prenotazione/prenotazioneAggiorna";
	}
	
	@RequestMapping(value="/modifica/prenotazione/{id}", method=RequestMethod.POST) 
	public String modificaPrenotazione(@PathVariable("id") Long id, @ModelAttribute("prenotazione") PrenotazioneCampo prenotazione, Model model,
			BindingResult bindingResult) {
		
		Cliente cliente = getCliente();
		PrenotazioneCampo vecchia = this.prenotazioneService.getById(id);
		
		
		if (!bindingResult.hasErrors()) {
			vecchia.updatePrenotazione(prenotazione);
			this.prenotazioneService.inserisci(vecchia);
		}
		
		model.addAttribute("prenotazioni", this.prenotazioneService.getByCliente(cliente));
		model.addAttribute("tipoCampi",this.tipoService.tuttiOrdinati());
		return "prenotazione/prenotazioni";
	}
}
